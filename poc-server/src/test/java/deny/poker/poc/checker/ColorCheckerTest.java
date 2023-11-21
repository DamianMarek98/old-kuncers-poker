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
class ColorCheckerTest {
    @Autowired
    ColorChecker colorChecker;

    @Test
    void shouldReturnFalseWhenNoCards() {
        assertFalse(colorChecker.contains(List.of(), null, Color.BLACK_CLUB));
    }

    @Test
    void shouldReturnFalseWhenNoFiveCardsOfSpecifiedColor() {
        assertFalse(colorChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.JACK), new Card(Color.BLACK_CLUB, Figure.TEN), new Card(Color.BLACK_CLUB, Figure.NINE), new Card(Color.BLACK_CLUB, Figure.QUEEN), new Card(Color.RED_DIAMOND, Figure.QUEEN)), null, Color.BLACK_CLUB));
    }

    @Test
    void shouldReturnTrueWhenFourOfGivenFigureExists() {
        assertTrue(colorChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.JACK), new Card(Color.BLACK_CLUB, Figure.TEN), new Card(Color.BLACK_CLUB, Figure.NINE), new Card(Color.BLACK_CLUB, Figure.QUEEN), new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.RED_DIAMOND, Figure.QUEEN)), null, Color.BLACK_CLUB));
    }
}
