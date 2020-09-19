package org.peidevs.waro.config;

import org.peidevs.waro.player.*;
import org.peidevs.waro.strategy.*;
import org.peidevs.waro.table.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class Config {
    // -----------------------------------
    // configure these as desired
    private static final int NUM_CARDS = 12;
    private static final int MAX_CARD = NUM_CARDS;
    private static final int NUM_GAMES = 1;
    private static final boolean IS_VERBOSE = true;

    // API remote strategy
    private static final String SCHEME = "http";
    private static final String HOST = "localhost:8080";
    private static final String PATH = "waro/strategy";
    private static final String MODE = "max";

    @Bean
    public List<Player> players() {
        var players = new ArrayList<Player>();

        // -----------------------------------
        // configure players as desired
        players.add(new Player("Liszt", new NextCard(), MAX_CARD));
        // players.add(new Player("You", new Console(), MAX_CARD));
        // players.add(new Player("Chopin", new ApiRemote(SCHEME, HOST, PATH, MODE), MAX_CARD));
        players.add(new Player("Beethoven", new NextCard(), MAX_CARD));
        players.add(new Player("Mozart", new NextCard(), MAX_CARD));

        return players;
    }

    @Bean public int numPlayers() { return players().size(); }
    @Bean public int numCards() { return NUM_CARDS; }
    @Bean public int numGames() { return NUM_GAMES; }
    @Bean public boolean isVerbose() { return IS_VERBOSE; }
    @Bean public DeckProvider deckProvider() {
        List<Integer> cards = new ArrayList<>(List.of(
            /* kitty */ 1,2,3,
            /* p1 */    4,5,12,
            /* p2 */    7,11,9,
            /* p3 */    10,8,6));
        return new FixedDeckProvider(cards);
    }

    protected static final String BEAN_NUM_PLAYERS = "numPlayers";
    protected static final String BEAN_NUM_CARDS = "numCards";
    protected static final String BEAN_NUM_GAMES = "numGames";
    protected static final String BEAN_IS_VERBOSE = "isVerbose";
    protected static final String BEAN_PLAYERS = "players";
    protected static final String BEAN_DECK_PROVIDER = "deckProvider";
}
