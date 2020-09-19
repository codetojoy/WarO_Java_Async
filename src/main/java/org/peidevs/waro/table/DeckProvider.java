package org.peidevs.waro.table;

import java.util.List;

public interface DeckProvider {
    List<Integer> buildDeck(int numCards);
}
