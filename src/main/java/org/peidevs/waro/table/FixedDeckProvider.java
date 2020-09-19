package org.peidevs.waro.table;

import java.util.List;

public class FixedDeckProvider implements DeckProvider {
    private final List<Integer> cards;

    public FixedDeckProvider(List<Integer> cards) {
        this.cards = cards;
    }

    @Override
    public List<Integer> buildDeck(int numCards) {
        return cards;
    }
}
