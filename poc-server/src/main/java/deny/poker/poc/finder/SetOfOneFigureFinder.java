package deny.poker.poc.finder;

import deny.poker.poc.Card;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class SetOfOneFigureFinder {

    private SetOfOneFigureFinder() {
    }

    static Optional<List<Card>> findHighestSetOfOneFigure(List<Card> cards, int amount) {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::figure))
                .entrySet()
                .stream()
                .filter((figureCardsEntry -> figureCardsEntry.getValue().size() >= amount))
                .max(Comparator.comparing(figureCardsEntry -> figureCardsEntry.getKey().getValue()))
                .map(Map.Entry::getValue)
                .map(set -> set.subList(0, amount));
    }
}
