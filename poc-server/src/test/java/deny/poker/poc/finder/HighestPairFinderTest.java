package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HighestPairFinderTest {
    @Autowired
    private HighestPairFinder highestPairFinder;

    @Test
    void givenSetOfCardsWithPairOfAcesFindHighestCardShouldReturnPairOfAces() {
        //given
        final Card ace1 = new Card(Color.RED_DIAMOND, Figure.ACE);
        final Card ace2 = new Card(Color.BLACK_CLUB, Figure.ACE);
        var cards = List.of(ace1, ace2, new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.RED_DIAMOND, Figure.NINE));
        //when
        var result = highestPairFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(ace1, ace2), result.get());
    }

    @Test
    void givenSetOfCardsWithPairOfNinesAndPairOfJacksFindHighestPairShouldReturnPairOfJacks() {
        //given
        final Card jack1 = new Card(Color.RED_DIAMOND, Figure.JACK);
        final Card jack2 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var cards = List.of(jack1, jack2, new Card(Color.BLACK_CLUB, Figure.NINE), new Card(Color.RED_DIAMOND, Figure.NINE));
        //when
        var result = highestPairFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(jack1, jack2), result.get());
    }

    @Test
    void givenSetOfCardsWithNoPairsFindHighestPairShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.RED_HEART, Figure.KING), new Card(Color.BLACK_CLUB, Figure.TEN), new Card(Color.RED_DIAMOND, Figure.NINE));
        //when
        var result = highestPairFinder.find(cards);
        //then
        assertTrue(result.isEmpty());
    }
}
