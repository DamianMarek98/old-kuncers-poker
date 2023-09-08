package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class HighestSetFinderTest {
    @Autowired
    private HighestSetFinder highestSetFinder;

    @Test
    void givenSetOfCardsWithNineTenAndQueenShouldReturnQueen() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var cards = List.of(nine, ten, queen);
        //when
        var result = highestSetFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertThat(result.get()).containsExactlyElementsOf(List.of(queen));
    }

    @Test
    void givenSetOfCardsWithTwoNinesOneTenAndOneQueenShouldReturnPairOfNines() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var cards = List.of(nine, ten, queen);
        //when
        var result = highestSetFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertThat(result.get()).containsExactlyElementsOf(List.of(queen));
    }
}
