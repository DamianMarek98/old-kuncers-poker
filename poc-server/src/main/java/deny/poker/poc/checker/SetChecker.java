package deny.poker.poc.checker;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;

import java.util.List;

public interface SetChecker {
    boolean contains(List<Card> cards, Figure figure, Color color);
}
