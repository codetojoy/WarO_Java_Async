package org.peidevs.waro.util;

import java.util.List;

import org.peidevs.waro.table.Hand;
import org.peidevs.waro.player.*;

public class Log {
    private final boolean isVerbose;

    public Log() {
        this(Boolean.TRUE);
    }

    public Log(boolean isVerbose) {
        this.isVerbose = isVerbose;
    }

    public void log(Player winner, int prizeCard) {
        if (isVerbose) {
            StringBuilder msg = new StringBuilder();
            msg.append("prize: " + prizeCard + " won by " + winner.getName());
            System.out.println(msg);
        }
    }

    public void log(List<Bid> bids) {
        if (isVerbose) {
            System.out.println("---------------------------------- ");
            for (var bid : bids) {
                StringBuilder msg = new StringBuilder();
                msg.append("prize: " + bid.prizeCard());
                msg.append(" bid: " + bid.offer());
                msg.append(" by: " + bid.bidder().toString());
                System.out.println(msg);
            }
        }
    }

    public void log(String msg, Hand kitty, List<Player> players) {
        if (isVerbose) {
            System.out.println("---------------------------------- " + msg);
            System.out.println("TRACER kitty : " + kitty);
            players.stream().forEach( p -> System.out.println("TRACER " + p) );
        }
    }

    public void log(String msg, List<Player> players) {
        log(msg, players, 0);
    }

    public void log(String msg, List<Player> players, int prizeCard) {
        if (isVerbose) {
            System.out.println("----------------------------------- " + msg);
            if (prizeCard != 0) {
                System.out.println("TRACER prize - " + prizeCard);
            }
            players.stream().forEach( p -> System.out.println("TRACER " + p) );
        }
    }

}
