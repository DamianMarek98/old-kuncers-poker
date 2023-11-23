package deny.poker.poc.game.check;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;

import java.util.List;

public interface CardsSetPresent {
    boolean checkHighCard(List<Card> cards, Figure figure);
    boolean checkPair(List<Card> cards, Figure pairFigure);
    boolean checkTwoPairs(List<Card> cards, Figure pairFigure1, Figure pairFigure2);
    boolean checkStraight(List<Card> cards, Figure from);
    boolean checkThreeOfAKind(List<Card> cards, Figure figure);
    boolean checkFullHouse(List<Card> cards, Figure figureTrio, Figure figurePair);
    boolean checkFourOfAKind(List<Card> cards, Figure figure);
    boolean checkColor(List<Card> cards, Color color);
    boolean checkPoker(List<Card> cards, Figure from, Color color);
}
