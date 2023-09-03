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
class HighCardFinderTest {
    @Autowired
    private HighCardFinder highCardFinder;

    @Test
    void givenSetOfCardsWithAceFindHighestCardShouldReturnAce() {
        //given
        final Card ace = new Card(Color.RED_DIAMOND, Figure.ACE);
        var cards = List.of(ace, new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.RED_DIAMOND, Figure.NINE));
        //when
        var result = highCardFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(ace), result.get());
    }

    @Test
    void givenSetOfCardsWithHighestCardJackFindHighestCardShouldReturnJack() {
        //given
        final Card jack = new Card(Color.RED_DIAMOND, Figure.JACK);
        var cards = List.of(jack, new Card(Color.BLACK_CLUB, Figure.TEN), new Card(Color.RED_DIAMOND, Figure.JACK));
        //when
        var result = highCardFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(jack), result.get());
    }
}
