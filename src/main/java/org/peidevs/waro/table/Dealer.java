package org.peidevs.waro.table;

import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

import org.apache.commons.lang3.tuple.ImmutablePair;
import com.google.common.collect.Lists;
import com.google.common.collect.Streams;

import org.peidevs.waro.player.*;

public class Dealer {

    public Table deal(int numPlayers, int numCards, Stream<Player> players, DeckProvider deckProvider) {
        var pair = deal(numCards, numPlayers, deckProvider);
        var hands = pair.getRight();

        // TODO: is there a zip function in JDK ?
        // https://stackoverflow.com/questions/17640754

        var newPlayers = Streams.zip(players, hands,
                                         (player, hand) -> player.reset(hand)).collect(toList());

        var kitty = pair.getLeft();
        var table = new Table(newPlayers, kitty);

        return table;
    }

    // ------- internal

    // @return pair with kitty and list of other hands
    protected ImmutablePair<Hand,Stream<Hand>> deal(int numCards, int numPlayers, DeckProvider deckProvider) {
        int numGroups = numPlayers + 1; // include kitty
        assertEvenNumberOfCards(numCards, numGroups);

        var deck = deckProvider.buildDeck(numCards);
        int numCardsPerHand = numCards / numGroups;

        // TODO: is there a way to partition using Java 8 ?
        var hands = Lists.partition(deck, numCardsPerHand);
        var kitty = new Hand(hands.get(0));
        var handsNoKittyList = hands.subList(1, hands.size());
        var handsNoKitty = handsNoKittyList.stream().map(cards -> new Hand(cards));
        var pair = new ImmutablePair<Hand, Stream<Hand>>(kitty, handsNoKitty);

        return pair;
    }

    protected void assertEvenNumberOfCards(int numCards, int numGroups) {
        if ((numCards % numGroups) != 0) {
            throw new IllegalArgumentException("uneven # of cards");
        }
    }
}
