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
public class TrioCheckerTest {
    @Autowired
    TrioChecker trioChecker;

    @Test
    void shouldReturnFalseWhenNoCards() {
        assertFalse(trioChecker.contains(List.of(), Figure.ACE, null));
    }

    @Test
    void shouldReturnFalseWhenNoThreeOfSpecifiedFigure() {
        assertFalse(trioChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.JACK), new Card(Color.RED_DIAMOND, Figure.JACK), new Card(Color.RED_HEART, Figure.NINE)), Figure.JACK, null));
    }

    @Test
    void shouldReturnTrueWhenThreeOfGivenFigureExists() {
        assertTrue(trioChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.JACK), new Card(Color.RED_DIAMOND, Figure.JACK), new Card(Color.RED_HEART, Figure.JACK), new Card(Color.RED_HEART, Figure.NINE)), Figure.JACK, null));
    }
}
