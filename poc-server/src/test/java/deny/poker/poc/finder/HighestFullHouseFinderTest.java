package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HighestFullHouseFinderTest {
    @Autowired
    HighestFullHouseFinder highestFullHouseFinder;

    @Test
    void givenSetOfCardsWithThreeJacksAndPairOfNinesFindHighestFullHouseShouldReturnThreeJacksAndPairOfNines() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var nine1 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var nine2 = new Card(Color.RED_HEART, Figure.NINE);
        var cards = List.of(jack1, jack2, jack3, nine1, nine2, new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING));
        //when
        var result = highestFullHouseFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertThat(result.get()).containsExactlyInAnyOrder(jack1, jack2, jack3, nine1, nine2);
    }

    @Test
    void givenSetOfCardsWithThreeJacksAndPairOfNinesAndThreeAcesFindHighestFullHouseShouldReturnThreeAcesAndPairOfJacks() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var ace1 = new Card(Color.BLACK_CLUB, Figure.ACE);
        var ace2 = new Card(Color.RED_HEART, Figure.ACE);
        var ace3 = new Card(Color.RED_DIAMOND, Figure.ACE);
        var nine1 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var nine2 = new Card(Color.RED_HEART, Figure.NINE);
        var cards = List.of(jack1, jack2, jack3, nine1, nine2, ace1, ace2, ace3);
        //when
        var result = highestFullHouseFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        var figureCardsResultMap = result.get().stream().collect(groupingBy(Card::figure));
        assertThat(figureCardsResultMap).containsOnlyKeys(Figure.ACE, Figure.JACK);
        assertThat(figureCardsResultMap.get(Figure.ACE)).hasSize(3);
        assertThat(figureCardsResultMap.get(Figure.JACK)).hasSize(2);
    }

    @Test
    void givenSetOfCardsWithThreeJacksAndPairOfNinesAndPairOfAcesFindHighestFullHouseShouldReturnThreeJacksAndPairOfAces() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var ace1 = new Card(Color.BLACK_CLUB, Figure.ACE);
        var ace2 = new Card(Color.RED_HEART, Figure.ACE);
        var nine1 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var nine2 = new Card(Color.RED_HEART, Figure.NINE);
        var cards = List.of(jack1, jack2, jack3, nine1, nine2, ace1, ace2);
        //when
        var result = highestFullHouseFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertThat(result.get()).containsExactlyInAnyOrder(ace1, ace2, jack1, jack2, jack3);
    }

    @Test
    void givenSetOfCardsWithTrioAndNoPairFindHighestFullHouseShouldReturnEmptyResult() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var cards = List.of(jack1, jack2, jack3, new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestFullHouseFinder.find(cards);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void givenSetOfCardsWithNoTrioAndPairFindHighestFullHouseShouldReturnEmptyResult() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var cards = List.of(jack1, jack2, new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestFullHouseFinder.find(cards);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void givenSetOfCardsWithNoTrioAndNoPairFindHighestFullHouseShouldReturnEmptyResult() {
        //given
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestFullHouseFinder.find(cards);
        //then
        assertFalse(result.isPresent());
    }
}
