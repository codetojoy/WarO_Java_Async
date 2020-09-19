package org.peidevs.waro.function;

import org.peidevs.waro.player.*;
import org.peidevs.waro.strategy.*;
import org.peidevs.waro.table.*;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class TourneyTest {
    @Test
    public void testApply_Basic() {
        int numGames = 3;
        int numCards = 12;
        int maxCard = numCards;
        var strategy = new NextCard();

        var p1 = new Player("p1", strategy, maxCard, new Hand());
        var p2 = new Player("p2", strategy, maxCard, new Hand());
        var p3 = new Player("p3", strategy, maxCard, new Hand());

        var players = new ArrayList<Player>(List.of(p1, p2, p3));

        var numPlayers = players.size();
        var deckProvider = new ShuffledDeckProvider();
        var tourney = new Tourney(numPlayers, numCards, numGames, false, deckProvider);

        // test
        var newPlayers = tourney.apply(players);

        assertEquals(3, newPlayers.size());
        assertEquals(0, newPlayers.get(0).getNumCardsInHand());
        assertEquals(0, newPlayers.get(1).getNumCardsInHand());
        assertEquals(0, newPlayers.get(2).getNumCardsInHand());
        assertEquals(3, newPlayers.stream().map(p -> p.getPlayerStats().numGamesWon()).mapToInt(i->i).sum());
    }
}
