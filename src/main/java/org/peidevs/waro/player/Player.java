package org.peidevs.waro.player;

import java.util.List;

import org.peidevs.waro.strategy.Strategy;
import org.peidevs.waro.strategy.StrategyExecutor;
import org.peidevs.waro.table.Hand;

public class Player {
    private final String name;
    private final Strategy strategy;
    private final PlayerStats playerStats;
    private final int maxCard;
    private final Hand hand;

    public Player(String name, Strategy strategy, int maxCard) {
        this(name, strategy, maxCard, new Hand(), new PlayerStats());
    }

    public Player(String name, Strategy strategy, int maxCard, Hand hand) {
        this(name, strategy, maxCard, hand, new PlayerStats());
    }

    // for testing:
    public Player(String name, PlayerStats playerStats) {
        this.name = name;
        this.strategy = null;
        this.maxCard = 5150;
        this.hand = null;
        this.playerStats = playerStats;
    }

    private Player(String name, Strategy strategy, int maxCard, Hand hand, PlayerStats playerStats) {
        this.name = name;
        this.strategy = strategy;
        this.maxCard = maxCard;
        this.hand = hand;
        this.playerStats = playerStats;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(name);
        buffer.append(" [" + hand.toString() + "]");
        buffer.append(" " + playerStats.toString());
        return buffer.toString();
    }

    public String toString(int numGames) {
        return "name: " + name + " won: " + playerStats.numGamesWon() + " of " + numGames + " games";
    }

    public int getNumGamesWon() { return playerStats.numGamesWon(); }

    public int getTotal() { return playerStats.total(); }

    public String getName() { return name; }

    public Player winsGame() {
        var newPlayerStats = playerStats.winsGame();
        var newPlayer = new Player(name, strategy, maxCard, hand, newPlayerStats);
        return newPlayer;
    }

    public Player winsRound(Bid bid) {
        var newHand = hand.select(bid.offer());
        var newPlayerStats = playerStats.winsRound(bid.prizeCard());
        var newPlayer = new Player(name, strategy, maxCard, newHand, newPlayerStats);
        return newPlayer;
    }

    public Player losesRound(Bid bid) {
        var newHand = hand.select(bid.offer());
        var newPlayer = new Player(name, strategy, maxCard, newHand, this.playerStats);
        return newPlayer;
    }

    public StrategyExecutor getStrategy(int prizeCard) {
        return new StrategyExecutor(strategy, prizeCard, hand.cardsAsIntStream(), maxCard, this);
    }

    public long getNumCardsInHand() {
        return hand.cardsAsIntStream().boxed().count();
    }

    public Player reset(Hand newHand) {
        var newPlayerStats = playerStats.reset();
        var newPlayer = new Player(name, strategy, maxCard, newHand, newPlayerStats);
        return newPlayer;
    }
}
