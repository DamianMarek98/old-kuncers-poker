package deny.poker.poc.checker;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import deny.poker.poc.checker.validation.CheckerValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class StraightChecker implements SetChecker {
    @Override
    @CheckerValidator(figureRequired = true)
    public boolean contains(List<Card> cards, Figure figure, Color color) {
        if (cards.isEmpty()) {
            return false;
        }

        return cards.stream()
                .filter(card -> card.figure().getValue() >= figure.getValue() && card.figure().getValue() <= figure.getValue() + 4)
                .map(Card::figure)
                .distinct()
                .count() == 5;
    }
}
