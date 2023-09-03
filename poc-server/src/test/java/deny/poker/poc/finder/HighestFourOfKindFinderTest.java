package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HighestFourOfKindFinderTest {
    @Autowired
    private HighestFourOfKindFinder highestFourOfKindFinder;

    @Test
    void givenSetOfCardsWithFourJacksFindFourOfKindShouldReturnFourJacks() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var jack4 = new Card(Color.BLACK_SPADE, Figure.JACK);
        var cards = List.of(jack1, jack2, jack3, new Card(Color.BLACK_CLUB, Figure.ACE), jack4, new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestFourOfKindFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertThat(result.get()).containsExactlyElementsOf(List.of(jack1, jack2, jack3, jack4));
    }

    @Test
    void givenSetOfCardsWithFourJacksAndFourAcesFindFourOfKindShouldReturnFourAces() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var jack4 = new Card(Color.BLACK_SPADE, Figure.JACK);
        var ace1 = new Card(Color.BLACK_CLUB, Figure.ACE);
        var ace2 = new Card(Color.RED_HEART, Figure.ACE);
        var ace3 = new Card(Color.RED_DIAMOND, Figure.ACE);
        var ace4 = new Card(Color.BLACK_SPADE, Figure.ACE);
        var cards = List.of(jack1, jack2, jack3, ace1, ace2, ace3, jack4, ace4, new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestFourOfKindFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertThat(result.get()).containsExactlyElementsOf(List.of(ace1, ace2, ace3, ace4));
    }

    @Test
    void givenSetOfCardsWithNoFourOfKindFindFourOfKindShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestFourOfKindFinder.find(cards);
        //then
        assertFalse(result.isPresent());
    }
}
