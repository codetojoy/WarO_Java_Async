package org.peidevs.waro.table;

import java.util.*;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

public class ShuffledDeckProvider implements DeckProvider {

    @Override
    public List<Integer> buildDeck(int numCards) {
        var cards = IntStream.range(1,numCards+1).boxed().collect(toList());
        Collections.shuffle(cards, new Random(new Date().getTime()));
        return cards;
    }
}
