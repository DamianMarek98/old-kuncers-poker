package deny.poker.poc.checker;

import deny.poker.poc.Card;
import deny.poker.poc.Figure;

import java.util.List;

class SetOfOneFigureChecker {
    private SetOfOneFigureChecker() {
    }

    static boolean containsSetOfOneFigure(List<Card> cards, Figure figure, int amount) {
        return cards.stream()
                .filter(card -> card.figure().equals(figure))
                .count() >= amount;
    }
}
