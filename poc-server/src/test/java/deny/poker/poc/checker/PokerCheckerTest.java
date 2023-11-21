package deny.poker.poc.checker;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PokerCheckerTest {
    @Autowired
    PokerChecker pokerChecker;

    @Test
    void shouldReturnFalseWhenNoCards() {
        assertFalse(pokerChecker.contains(List.of(), Figure.ACE, Color.BLACK_CLUB));
    }

    @Test
    void shouldReturnFalseWhenNoPokerFromSpecifiedFigureAndColor() {
        assertFalse(pokerChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.JACK), new Card(Color.RED_HEART, Figure.JACK)), Figure.ACE, Color.RED_HEART));
    }

    @Test
    void shouldReturnFalseWhenPokerFromSpecifiedFigureNotPossible() {
        assertFalse(pokerChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, Figure.TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE)), Figure.JACK, Color.RED_HEART));
    }

    @Test
    void shouldReturnTrueWhenContainsPokerFromSpecifiedFigureAndColor() {
        assertTrue(pokerChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, Figure.TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE)), Figure.TEN, Color.RED_HEART));
    }
}
