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
class PairCheckerTest {
    @Autowired
    PairChecker pairChecker;

    @Test
    void shouldReturnFalseWhenNoCards() {
        assertFalse(pairChecker.contains(List.of(), Figure.ACE, null));
    }

    @Test
    void shouldReturnFalseWhenNoPairOfSpecifiedFigure() {
        assertFalse(pairChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.JACK), new Card(Color.RED_HEART, Figure.JACK)), Figure.ACE, null));
    }

    @Test
    void shouldReturnTrueWhenPairOfGivenFigureExists() {
        assertTrue(pairChecker.contains(List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.RED_HEART, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.JACK)), Figure.ACE, null));
    }
}
