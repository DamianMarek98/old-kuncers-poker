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
class HighCardCheckerTest {
    @Autowired
    HighCardChecker highCardChecker;

    @Test
    void shouldReturnFalseWhenNoCards() {
        assertFalse(highCardChecker.contains(List.of(), Figure.ACE, null));
    }

    @Test
    void shouldReturnFalseWhenNoCardOfSpecifiedFigure() {
        assertFalse(highCardChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.JACK), new Card(Color.BLACK_CLUB, Figure.QUEEN)), Figure.ACE, null));
    }

    @Test
    void shouldReturnTrueWhenCardOfGivenFigureExists() {
        assertTrue(highCardChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.QUEEN)), Figure.ACE, null));
    }
}
