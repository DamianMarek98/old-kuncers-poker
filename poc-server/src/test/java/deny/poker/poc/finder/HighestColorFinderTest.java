package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class HighestColorFinderTest {
    @Autowired
    private HighestColorFinder highestColorFinder;

    @Test
    void givenSetOfCardsWithNoFiveCardsOfOneColorShouldReturnEmptyResult() {
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
        var result = highestColorFinder.find(cards);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void givenSetOfCardsWithFiveRedHeartColorShouldReturnFiveRedHeartCards() {
        //given
        var redHeart1 = new Card(Color.RED_HEART, Figure.NINE);
        var redHeart2 = new Card(Color.RED_HEART, Figure.JACK);
        var redHeart3 = new Card(Color.RED_HEART, Figure.QUEEN);
        var redHeart4 = new Card(Color.RED_HEART, Figure.TEN);
        var redHeart5 = new Card(Color.RED_HEART, Figure.KING);
        var ace1 = new Card(Color.RED_DIAMOND, Figure.ACE);
        var ace2 = new Card(Color.BLACK_SPADE, Figure.ACE);
        var cards = List.of(redHeart1, redHeart2, redHeart3, ace1, ace2, ace1, redHeart4, redHeart5, ace2, new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestColorFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> assertThat(set).containsExactlyInAnyOrder(redHeart1, redHeart2, redHeart3, redHeart4, redHeart5),
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithFiveRedHeartsAndFiveBlackSpadesShouldReturnFiveBlackSpadeCards() {
        //given
        var redHeart1 = new Card(Color.RED_HEART, Figure.NINE);
        var redHeart2 = new Card(Color.RED_HEART, Figure.JACK);
        var redHeart3 = new Card(Color.RED_HEART, Figure.QUEEN);
        var redHeart4 = new Card(Color.RED_HEART, Figure.TEN);
        var redHeart5 = new Card(Color.RED_HEART, Figure.KING);
        var blackSpade1 = new Card(Color.BLACK_SPADE, Figure.NINE);
        var blackSpade2 = new Card(Color.BLACK_SPADE, Figure.JACK);
        var blackSpade3 = new Card(Color.BLACK_SPADE, Figure.QUEEN);
        var blackSpade4 = new Card(Color.BLACK_SPADE, Figure.TEN);
        var blackSpade5 = new Card(Color.BLACK_SPADE, Figure.KING);
        var blackSpade6 = new Card(Color.BLACK_SPADE, Figure.ACE);
        var ace1 = new Card(Color.RED_DIAMOND, Figure.ACE);
        var cards = List.of(blackSpade1, blackSpade2, blackSpade3, redHeart1, redHeart2, blackSpade4, redHeart3, blackSpade5, ace1, blackSpade6, ace1, redHeart4, redHeart5, new Card(Color.BLACK_CLUB, Figure.KING), new Card(Color.BLACK_CLUB, Figure.NINE));
        //when
        var result = highestColorFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> assertThat(set).containsExactlyInAnyOrder(blackSpade1, blackSpade2, blackSpade3, blackSpade4, blackSpade5, blackSpade6),
                Assertions::fail);
    }
}
