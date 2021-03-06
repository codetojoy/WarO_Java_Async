package org.peidevs.waro.function;

import java.util.List;
import java.util.stream.*;
import java.util.function.*;
import static java.util.stream.Collectors.toList;

import org.peidevs.waro.player.*;
import org.peidevs.waro.table.*;
import org.peidevs.waro.util.*;

public class Tourney implements UnaryOperator<List<Player>> {
    private final Log logger;
    private final boolean isVerbose;
    private final int numPlayers;
    private final int numCards;
    private final int numGames;
    private final DeckProvider deckProvider;

    public Tourney(int numPlayers, int numCards, int numGames, boolean isVerbose, DeckProvider deckProvider) {
        this.numPlayers = numPlayers;
        this.numCards = numCards;
        this.numGames = numGames;
        this.logger = new Log(isVerbose);
        this.isVerbose = isVerbose;
        this.deckProvider = deckProvider;
    }

    @Override
    public List<Player> apply(List<Player> players) {
        UnaryOperator<List<Player>> game = new Game(numPlayers, numCards, isVerbose, deckProvider);
        Stream<List<Player>> stream = Stream.iterate(players, game).limit(numGames + 1);
        List<List<Player>> results = stream.collect(toList());
        var newPlayers = results.get(results.size() - 1);
        logger.log("END TOURNEY", newPlayers);

        return newPlayers;
    }
}
