package deny.poker.poc.checker;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import deny.poker.poc.checker.validation.CheckerValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HighCardChecker implements SetChecker {
    @Override
    @CheckerValidator(figureRequired = true)
    public boolean contains(List<Card> cards, Figure figure, Color color) {
        return cards.stream().anyMatch(card -> card.figure().equals(figure));
    }
}
