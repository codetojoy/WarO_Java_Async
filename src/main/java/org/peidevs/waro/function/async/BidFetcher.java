
package org.peidevs.waro.function.async;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

import org.peidevs.waro.player.Bid;

public class BidFetcher {

    protected List<Bid> internalFetchBids(List<Supplier<Bid>> tasks) throws Exception {
        List<CompletableFuture<Bid>> futures = new ArrayList<>();

        for (var task : tasks) {
            var f = CompletableFuture.supplyAsync(task);
            futures.add(f);
        }

        var futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        var allFutures = CompletableFuture.allOf(futuresArray);

        // CompletableFuture<List<Bid>>
        var compoundFuture = allFutures.thenApply(v -> {
            return futures.stream()
                          .map(f -> f.join())
                          .collect(Collectors.toList());
        });

        var results = compoundFuture.get();
        return results;
    }

    public List<Bid> fetchBids(List<Supplier<Bid>> tasks) {
        List<Bid> bids = null;

        try {
            bids = internalFetchBids(tasks);
        } catch (Exception ex) {
            System.err.println("ERROR: caught exception: " + ex.getMessage());
            // just bail out ¯\_(ツ)_/¯
            System.exit(-1);
        }

        return bids;
    }
}
