package deny.poker.poc;

import java.util.*;
import java.util.stream.Collectors;

public class SetsFinder {
    private SetsFinder() {
    }

    public static Optional<List<Card>> findHighestCard(List<Card> cards) {
        return cards.stream()
                .max(Comparator.comparing(card -> card.figure().getValue()))
                .map(List::of);
    }

    public static Optional<List<Card>> findHighestPair(List<Card> cards) {
        return findHighestSetOfOneFigure(cards, 2);
    }

    public static Optional<List<Card>> findTwoHighestPairs(List<Card> cards) {
        var cardsInPlay = new ArrayList<>(cards);
        var highestPair = findHighestPair(cardsInPlay);
        if (highestPair.isEmpty()) {
            return Optional.empty();
        }
        cardsInPlay.removeAll(highestPair.get());
        var secondHighestPair = findHighestPair(cardsInPlay);
        if (secondHighestPair.isEmpty()) {
            return Optional.empty();
        }
        var pairs = highestPair.get();
        pairs.addAll(secondHighestPair.get());
        return Optional.of(pairs);
    }

    public static Optional<List<Card>> findHighestTrio(List<Card> cards) {
        return findHighestSetOfOneFigure(cards, 3);
    }

    public static Optional<List<Card>> findHighestFourOfKind(List<Card> cards) {
        return findHighestSetOfOneFigure(cards, 4);
    }

    private static Optional<List<Card>> findHighestSetOfOneFigure(List<Card> cards, int amount) {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::figure))
                .entrySet()
                .stream()
                .filter((figureCardsEntry -> figureCardsEntry.getValue().size() == amount))
                .max(Comparator.comparing(figureCardsEntry -> figureCardsEntry.getKey().getValue()))
                .map(Map.Entry::getValue);
    }
}
