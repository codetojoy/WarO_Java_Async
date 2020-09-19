package org.peidevs.waro.table;

import static org.junit.Assert.*;
import org.junit.*;

public class ShuffledDeckProviderTest {

    @Test
    public void testBuildShuffledDeck() {
        int numCards = 4;
        var shuffledDeckProvider = new ShuffledDeckProvider();

        // test
        var result = shuffledDeckProvider.buildDeck(numCards);

        assertEquals(4, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
    }
}
