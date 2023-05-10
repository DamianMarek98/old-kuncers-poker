package deny.poker.poc;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetsFinderTest {

    @Test
    void givenSetOfCardsWithAceFindHighestCardShouldReturnAce() {
        //given
        final Card ace = new Card(Color.RED_DIAMOND, Figure.ACE);
        var cards = List.of(ace, new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.RED_DIAMOND, Figure.NINE));
        //when
        var result = SetsFinder.findHighestCard(cards);
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
        var result = SetsFinder.findHighestCard(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(jack), result.get());
    }

    @Test
    void givenSetOfCardsWithPairOfAcesFindHighestCardShouldReturnPairOfAces() {
        //given
        final Card ace1 = new Card(Color.RED_DIAMOND, Figure.ACE);
        final Card ace2 = new Card(Color.BLACK_CLUB, Figure.ACE);
        var cards = List.of(ace1, ace2, new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.RED_DIAMOND, Figure.NINE));
        //when
        var result = SetsFinder.findHighestPair(cards);
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
        var result = SetsFinder.findHighestPair(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(jack1, jack2), result.get());
    }

    @Test
    void givenSetOfCardsWithNoPairsFindHighestPairShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.RED_HEART, Figure.KING), new Card(Color.BLACK_CLUB, Figure.TEN), new Card(Color.RED_DIAMOND, Figure.NINE));
        //when
        var result = SetsFinder.findHighestPair(cards);
        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void givenSetOfCardsWithPairOfNinesAndPairOfJacksFindTwoHighestPairsShouldReturnPairOfNinesAndPairOfJacks() {
        //given
        final Card jack1 = new Card(Color.RED_DIAMOND, Figure.JACK);
        final Card jack2 = new Card(Color.BLACK_CLUB, Figure.JACK);
        final Card nine1 = new Card(Color.BLACK_CLUB, Figure.NINE);
        final Card nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var cards = List.of(jack1, jack2, nine1, nine2, new Card(Color.BLACK_CLUB, Figure.ACE));
        //when
        var result = SetsFinder.findTwoHighestPairs(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(jack1, jack2, nine1, nine2), result.get());
    }

    @Test
    void givenSetOfCardsWithPairOfNinesAndPairOfJacksAndPairOfAcesFindTwoHighestPairsShouldReturnPairOfAcesAndPairOfJacks() {
        //given
        final Card jack1 = new Card(Color.RED_DIAMOND, Figure.JACK);
        final Card jack2 = new Card(Color.BLACK_CLUB, Figure.JACK);
        final Card ace1 = new Card(Color.BLACK_CLUB, Figure.ACE);
        final Card ace2 = new Card(Color.RED_DIAMOND, Figure.ACE);
        var cards = List.of(ace1, ace2, jack1, jack2, new Card(Color.BLACK_CLUB, Figure.NINE), new Card(Color.RED_HEART, Figure.NINE), new Card(Color.BLACK_CLUB, Figure.TEN));
        //when
        var result = SetsFinder.findTwoHighestPairs(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(ace1, ace2, jack1, jack2), result.get());
    }

    @Test
    void givenSetOfCardsWithPairOfNinesFindTwoHighestPairsShouldReturnEmptyResult() {
        //given
        final Card nine1 = new Card(Color.BLACK_CLUB, Figure.NINE);
        final Card nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var cards = List.of(nine1, nine2, new Card(Color.BLACK_CLUB, Figure.ACE));
        //when
        var result = SetsFinder.findTwoHighestPairs(cards);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void givenSetOfCardsWithNoPairsFindTwoHighestPairsShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = SetsFinder.findTwoHighestPairs(cards);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void givenSetOfCardsWithThreeJacksFindHighestTrioShouldReturnThreeJacks() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var cards = List.of(jack1, jack2, jack3, new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = SetsFinder.findHighestTrio(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(jack1, jack2, jack3), result.get());
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
        var result = SetsFinder.findHighestTrio(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(ace1, ace2, ace3), result.get());
    }

    @Test
    void givenSetOfCardsWithNoTrioFindHighestTrioShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = SetsFinder.findHighestTrio(cards);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void givenSetOfCardsWithFourJacksFindFourOfKindShouldReturnFourJacks() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var jack4 = new Card(Color.BLACK_SPADE, Figure.JACK);
        var cards = List.of(jack1, jack2, jack3, new Card(Color.BLACK_CLUB, Figure.ACE), jack4, new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = SetsFinder.findHighestFourOfKind(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(jack1, jack2, jack3, jack4), result.get());
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
        var result = SetsFinder.findHighestFourOfKind(cards);
        //then
        assertTrue(result.isPresent());
        assertEquals(List.of(ace1, ace2, ace3, ace4), result.get());
    }

    @Test
    void givenSetOfCardsWithNoFourOfKindFindFourOfKindShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = SetsFinder.findHighestFourOfKind(cards);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void givenSetOfCardsWithAllFiguresFindHighestStraightShouldReturnStraightFromTenToAce() {
        //given
        Card ten = new Card(Color.BLACK_CLUB, Figure.TEN);
        Card jack = new Card(Color.BLACK_CLUB, Figure.JACK);
        Card queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        Card king = new Card(Color.BLACK_CLUB, Figure.KING);
        Card ace = new Card(Color.RED_HEART, Figure.ACE);
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE), ten, jack, queen, king, ace);
        //when
        var result = SetsFinder.findHighestStraight(cards);
        //then
        assertEquals(List.of(ten, jack,  queen, king, ace), result.get());
    }

    @Test
    void givenSetOfCardsWithAllFiguresFromNineToAceFindHighestStraightShouldReturnStraightFromNineToAce() {
        //given
        Card nine = new Card(Color.RED_HEART, Figure.NINE);
        Card ten = new Card(Color.BLACK_CLUB, Figure.TEN);
        Card jack = new Card(Color.BLACK_CLUB, Figure.JACK);
        Card queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        Card king = new Card(Color.BLACK_CLUB, Figure.KING);
        var cards = List.of(nine, ten, jack, queen, king, new Card(Color.RED_HEART, Figure.JACK));
        //when
        var result = SetsFinder.findHighestStraight(cards);
        //then
        assertEquals(List.of(nine, ten, jack,  queen, king), result.get());
    }

    @Test
    void givenSetOfCardsWithNoStraightFindHighestStraightShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = SetsFinder.findHighestStraight(cards);
        //then
        assertFalse(result.isPresent());
    }
}
