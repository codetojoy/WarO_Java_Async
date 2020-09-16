package org.peidevs.waro.strategy;

import java.util.*;
import java.util.stream.*;

public final class Console implements Strategy {
    @Override
    public int selectCard(int prizeCard, IntStream hand, int maxCard) {
        var bid = 0;

        var cards = hand.boxed().collect(Collectors.toList());
        System.out.println("\nCard in play is " + prizeCard);
        System.out.print("\nYour hand is ");
        for (var card : cards) {
            System.out.print(card + " ");
        }
        System.out.println("");

        var ok = false;
        var scanner = new Scanner(System.in);

        while (!ok) {
            System.out.println("Enter your bid: ");
            bid = scanner.nextInt();

            if (cards.contains(bid)) {
                ok = true;
            } else {
                System.err.println("illegal bid!");
            }
        }

        return bid;
    }
}
