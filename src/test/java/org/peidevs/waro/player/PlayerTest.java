package org.peidevs.waro.player;

import org.peidevs.waro.strategy.*;
import org.peidevs.waro.table.Hand;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class PlayerTest {

    @Test
    public void testReset_Basic() {
        var strategy = new MaxCard();
        int maxCard = 40;
        int prizeCard = 10;
        var cards = IntStream.range(1,5).boxed().collect(toList());
        var hand = new Hand(cards);
        var player = new Player("Randy", strategy, maxCard, hand);
        var strategyExecutor = player.getStrategy(prizeCard);
        var bid = strategyExecutor.get();
        player = player.winsRound(bid);
        assertEquals(3, player.getNumCardsInHand());
        assertEquals(1, player.getPlayerStats().numRoundsWon());

        var newCards = IntStream.range(6,8+1).boxed().collect(toList());
        var newHand = new Hand(newCards);

        // test
        var result = player.reset(newHand);

        assertEquals(3, result.getNumCardsInHand());
        assertEquals(0, result.getPlayerStats().numRoundsWon());
    }

    /*
    @Test
    public void testGetStrategy_Basic() {
        var strategy = new MaxCard();
        int maxCard = 40;
        int prizeCard = 10;
        var cards = IntStream.range(1,5).boxed().collect(toList());
        var hand = new Hand(cards);
        var player = new Player("Randy", strategy, maxCard, hand);

        // test
        var strategy = player.getStrategy(prizeCard);

        assertEquals(player, bid.bidder());
        assertEquals(4, bid.offer());
        assertEquals(prizeCard, bid.prizeCard());
   }
   */
}
