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
class StraightCheckerTest {
    @Autowired
    StraightChecker straightChecker;

    @Test
    void shouldReturnFalseWhenNoCards() {
        assertFalse(straightChecker.contains(List.of(), Figure.ACE, null));
    }

    @Test
    void shouldReturnFalseWhenNoStraightFromSpecifiedFigure() {
        assertFalse(straightChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.JACK), new Card(Color.RED_HEART, Figure.JACK)), Figure.ACE, null));
    }

    @Test
    void shouldReturnFalseWhenStraightFromSpecifiedFigureNotPossible() {
        assertFalse(straightChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, Figure.TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE)), Figure.JACK, null));
    }

    @Test
    void shouldReturnTrueWhenContainsStraightFromSpecifiedFigure() {
        assertTrue(straightChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, Figure.TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE)), Figure.NINE, null));
    }
}
