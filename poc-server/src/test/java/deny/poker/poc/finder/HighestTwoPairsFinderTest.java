package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class HighestTwoPairsFinderTest {
    @Autowired
    private HighestTwoPairsFinder highestTwoPairsFinder;

    @Test
    void givenSetOfCardsWithNoPairsFindTwoHighestPairsShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestTwoPairsFinder.find(cards);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void givenSetOfCardsWithOnePairFindTwoHighestPairsShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.RED_DIAMOND, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestTwoPairsFinder.find(cards);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void givenSetOfCardsWithTwoPairFindTwoHighestPairsShouldReturnTheseTwoPairs() {
        //given
        final Card ace1 = new Card(Color.BLACK_CLUB, Figure.ACE);
        final Card ace2 = new Card(Color.RED_DIAMOND, Figure.ACE);
        final Card king1 = new Card(Color.BLACK_CLUB, Figure.KING);
        final Card king2 = new Card(Color.RED_HEART, Figure.KING);
        var cards = List.of(king1, ace1, king2, ace2, new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestTwoPairsFinder.find(cards);
        //then
        assertThat(result).isPresent();
        assertThat(result.get()).hasSameElementsAs(List.of(ace1, ace2, king1, king2));
    }

    @Test
    void givenSetOfCardsWithThreePairFindTwoHighestPairsShouldReturnTheseTwoPairsOfAcesAndKings() {
        //given
        final Card ace1 = new Card(Color.BLACK_CLUB, Figure.ACE);
        final Card ace2 = new Card(Color.RED_DIAMOND, Figure.ACE);
        final Card king1 = new Card(Color.BLACK_CLUB, Figure.KING);
        final Card king2 = new Card(Color.RED_HEART, Figure.KING);
        final Card nine1 = new Card(Color.BLACK_CLUB, Figure.NINE);
        final Card nine2 = new Card(Color.RED_HEART, Figure.NINE);
        var cards = List.of(nine1, king1, ace1, nine2, king2, ace2, new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestTwoPairsFinder.find(cards);
        //then
        assertThat(result).isPresent();
        assertThat(result.get()).hasSameElementsAs(List.of(ace1, ace2, king1, king2));
    }
}
