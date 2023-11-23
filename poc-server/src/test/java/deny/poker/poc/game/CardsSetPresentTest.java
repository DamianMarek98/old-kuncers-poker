package deny.poker.poc.game;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import deny.poker.poc.game.check.CardsSetPresent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static deny.poker.poc.Figure.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CardsSetPresentTest {
    @Autowired
    CardsSetPresent cardsSetPresent;

    @Test
    void shouldReturnTrueWhenContainsHighCard() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, Figure.ACE));
        assertTrue(cardsSetPresent.checkHighCard(cards, ACE));
    }

    @Test
    void shouldReturnFalseWhenNoHighCard() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_DIAMOND, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK));
        assertFalse(cardsSetPresent.checkHighCard(cards, ACE));
    }

    @Test
    void shouldReturnTrueWhenContainsPair() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.ACE));
        assertTrue(cardsSetPresent.checkPair(cards, JACK));
    }

    @Test
    void shouldReturnFalseWhenNoPair() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE));
        assertFalse(cardsSetPresent.checkPair(cards, TEN));
    }

    @Test
    void shouldReturnTrueWhenContainsTwoPairs() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.BLACK_CLUB, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.ACE));
        assertTrue(cardsSetPresent.checkTwoPairs(cards, JACK, QUEEN));
    }

    @Test
    void shouldReturnFalseWhenNoTwoPairs() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_DIAMOND, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE));
        assertFalse(cardsSetPresent.checkTwoPairs(cards, TEN, ACE));
    }

    @Test
    void shouldReturnTrueWhenContainsStraight() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE));
        assertTrue(cardsSetPresent.checkStraight(cards, TEN));
    }

    @Test
    void shouldReturnFalseWhenNoStraight() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_DIAMOND, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING));
        assertFalse(cardsSetPresent.checkPoker(cards, TEN, Color.RED_HEART));
    }

    @Test
    void shouldReturnTrueWhenContainsTrio() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, TEN),
                new Card(Color.BLACK_CLUB, TEN),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.BLACK_SPADE, TEN),
                new Card(Color.RED_HEART, Figure.ACE));
        assertTrue(cardsSetPresent.checkThreeOfAKind(cards, TEN));
    }

    @Test
    void shouldReturnFalseWhenNoTrio() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_DIAMOND, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE));
        assertFalse(cardsSetPresent.checkThreeOfAKind(cards, TEN));
    }

    @Test
    void shouldReturnTrueWhenContainsFullHouse() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.ACE),
                new Card(Color.RED_HEART, TEN),
                new Card(Color.BLACK_CLUB, TEN),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.BLACK_SPADE, TEN),
                new Card(Color.RED_HEART, Figure.ACE));
        assertTrue(cardsSetPresent.checkFullHouse(cards, TEN, ACE));
    }

    @Test
    void shouldReturnFalseWhenNoFullHouse() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_DIAMOND, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE));
        assertFalse(cardsSetPresent.checkFullHouse(cards, TEN, ACE));
    }

    @Test
    void shouldReturnTrueWhenContainsFourOfAKind() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, TEN),
                new Card(Color.BLACK_CLUB, TEN),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_DIAMOND, TEN),
                new Card(Color.BLACK_SPADE, TEN),
                new Card(Color.RED_HEART, Figure.ACE));
        assertTrue(cardsSetPresent.checkFourOfAKind(cards, TEN));
    }

    @Test
    void shouldReturnFalseWhenNoFourOfAKind() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_DIAMOND, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE));
        assertFalse(cardsSetPresent.checkThreeOfAKind(cards, TEN));
    }

    @Test
    void shouldReturnTrueWhenContainsColor() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE));
        assertTrue(cardsSetPresent.checkColor(cards, Color.RED_HEART));
    }

    @Test
    void shouldReturnFalseWhenNoColor() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_DIAMOND, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE));
        assertFalse(cardsSetPresent.checkPoker(cards, TEN, Color.RED_HEART));
    }

    @Test
    void shouldReturnTrueWhenContainsPoker() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_HEART, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE));
        assertTrue(cardsSetPresent.checkPoker(cards, TEN, Color.RED_HEART));
    }

    @Test
    void shouldReturnFalseWhenNoPoker() {
        var cards = List.of(new Card(Color.BLACK_CLUB, Figure.NINE),
                new Card(Color.RED_DIAMOND, TEN),
                new Card(Color.BLACK_CLUB, Figure.JACK),
                new Card(Color.RED_HEART, Figure.JACK),
                new Card(Color.RED_HEART, Figure.QUEEN),
                new Card(Color.RED_HEART, Figure.KING),
                new Card(Color.RED_HEART, Figure.ACE));
        assertFalse(cardsSetPresent.checkPoker(cards, TEN, Color.RED_HEART));
    }
}
