package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HighestTrioFinderTest {
    @Autowired
    private HighestTrioFinder highestTrioFinder;

    @Test
    void givenSetOfCardsWithThreeJacksFindHighestTrioShouldReturnThreeJacks() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var cards = List.of(jack1, jack2, jack3, new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestTrioFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(jack1, jack2, jack3), result.get());
    }

    @Test
    void givenSetOfCardsWithFourJacksFindHighestTrioShouldReturnThreeJacks() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var jack4 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var cards = List.of(jack1, jack2, jack3, jack4, new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestTrioFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertThat(result.get()).hasSize(3);
        assertTrue(result.get().stream().allMatch(card -> card.figure().equals(Figure.JACK)));
    }

    @Test
    void givenSetOfCardsWithThreeJacksAndThreeAcesFindHighestTrioShouldReturnThreeAces() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var ace1 = new Card(Color.BLACK_CLUB, Figure.ACE);
        var ace2 = new Card(Color.RED_HEART, Figure.ACE);
        var ace3 = new Card(Color.RED_DIAMOND, Figure.ACE);
        var cards = List.of(jack1, jack2, jack3, ace1, ace2, ace3, new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestTrioFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(ace1, ace2, ace3), result.get());
    }

    @Test
    void givenSetOfCardsWithNoTrioFindHighestTrioShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestTrioFinder.find(cards);
        //then
        assertFalse(result.isPresent());
    }
}
