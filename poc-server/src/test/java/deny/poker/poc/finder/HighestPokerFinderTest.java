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
class HighestPokerFinderTest {
    @Autowired
    private HighestPokerFinder highestPokerFinder;

    @Test
    void givenSetOfCardsWithoutPokerShouldReturnEmptyResult() {
        //given
        var jack1 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var jack2 = new Card(Color.RED_HEART, Figure.JACK);
        var jack3 = new Card(Color.RED_DIAMOND, Figure.JACK);
        var nine1 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var nine2 = new Card(Color.RED_HEART, Figure.NINE);
        var cards = List.of(jack1, jack2, jack3, nine1, nine2, new Card(Color.BLACK_CLUB, Figure.ACE), new Card(Color.BLACK_CLUB, Figure.KING));
        //when
        var result = highestPokerFinder.find(cards);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void givenSetOfCardsWithSmallRedHeartPokerShouldReturnSmallRedHeartPoker() {
        //given
        var nine = new Card(Color.RED_HEART, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var queen = new Card(Color.RED_HEART, Figure.QUEEN);
        var queen2 = new Card(Color.BLACK_SPADE, Figure.QUEEN);
        var king = new Card(Color.RED_HEART, Figure.KING);
        var cards = List.of(nine, ten, jack, queen, queen2, king);
        //when
        var result = highestPokerFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertTrue(result.get().stream().allMatch(card -> card.color().equals(Color.RED_HEART)));
        var resultGroupedByFigure = result.get().stream().collect(groupingBy(Card::figure));
        assertThat(resultGroupedByFigure)
                .containsOnlyKeys(Figure.NINE, Figure.TEN, Figure.JACK, Figure.QUEEN, Figure.KING);
        assertTrue(resultGroupedByFigure.values().stream()
                .allMatch(cardsOfTheSameFigure -> cardsOfTheSameFigure.size() == 1));
    }

    @Test
    void givenSetOfCardsWithSmallRedHearthPokerAndBigReadHartPokerShouldReturnBigReadHartPoker() {
        //given
        var nine = new Card(Color.RED_HEART, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var queen = new Card(Color.RED_HEART, Figure.QUEEN);
        var queen2 = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var king = new Card(Color.RED_HEART, Figure.KING);
        var ace = new Card(Color.RED_HEART, Figure.ACE);
        var ace2 = new Card(Color.RED_DIAMOND, Figure.ACE);
        var cards = List.of(nine, ten, jack, queen, queen2, king, ace, ace2);
        //when
        var result = highestPokerFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertTrue(result.get().stream().allMatch(card -> card.color().equals(Color.RED_HEART)));
        var resultGroupedByFigure = result.get().stream().collect(groupingBy(Card::figure));
        assertThat(resultGroupedByFigure)
                .containsOnlyKeys(Figure.TEN, Figure.JACK, Figure.QUEEN, Figure.KING, Figure.ACE);
        assertTrue(resultGroupedByFigure.values().stream()
                .allMatch(cardsOfTheSameFigure -> cardsOfTheSameFigure.size() == 1));
    }

    @Test
    void givenSetOfCardsWithBigRedHearthPokerAndBigBlackSpadePokerShouldReturnBigBlackSpadePoker() {
        //given
        var nine = new Card(Color.RED_HEART, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var queen = new Card(Color.RED_HEART, Figure.QUEEN);
        var queen2 = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var king = new Card(Color.RED_HEART, Figure.KING);
        var ace = new Card(Color.RED_HEART, Figure.ACE);
        var nine2 = new Card(Color.BLACK_SPADE, Figure.NINE);
        var ten2 = new Card(Color.BLACK_SPADE, Figure.TEN);
        var jack2 = new Card(Color.BLACK_SPADE, Figure.JACK);
        var queen3 = new Card(Color.BLACK_SPADE, Figure.QUEEN);
        var king2 = new Card(Color.BLACK_SPADE, Figure.KING);
        var ace2 = new Card(Color.BLACK_SPADE, Figure.ACE);
        var cards = List.of(nine, nine2, ten, ten2, jack, jack2, queen, queen2, queen3, king, king2, ace, ace2);
        //when
        var result = highestPokerFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertTrue(result.get().stream().allMatch(card -> card.color().equals(Color.BLACK_SPADE)));
        var resultGroupedByFigure = result.get().stream().collect(groupingBy(Card::figure));
        assertThat(resultGroupedByFigure)
                .containsOnlyKeys(Figure.TEN, Figure.JACK, Figure.QUEEN, Figure.KING, Figure.ACE);
        assertTrue(resultGroupedByFigure.values().stream()
                .allMatch(cardsOfTheSameFigure -> cardsOfTheSameFigure.size() == 1));
    }

    @Test
    void givenSetOfCardsWithBigRedHearthPokerAndSmallBlackSpadePokerShouldReturnBigRedHearthPoker() {
        //given
        var nine = new Card(Color.RED_HEART, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var queen = new Card(Color.RED_HEART, Figure.QUEEN);
        var queen2 = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var king = new Card(Color.RED_HEART, Figure.KING);
        var ace = new Card(Color.RED_HEART, Figure.ACE);
        var nine2 = new Card(Color.BLACK_SPADE, Figure.NINE);
        var ten2 = new Card(Color.BLACK_SPADE, Figure.TEN);
        var jack2 = new Card(Color.BLACK_SPADE, Figure.JACK);
        var queen3 = new Card(Color.BLACK_SPADE, Figure.QUEEN);
        var king2 = new Card(Color.BLACK_SPADE, Figure.KING);
        var cards = List.of(nine, nine2, ten, ten2, jack, jack2, queen, queen2, queen3, king, king2, ace);
        //when
        var result = highestPokerFinder.find(cards);
        //then
        assertTrue(result.isPresent());
        assertTrue(result.get().stream().allMatch(card -> card.color().equals(Color.RED_HEART)));
        var resultGroupedByFigure = result.get().stream().collect(groupingBy(Card::figure));
        assertThat(resultGroupedByFigure)
                .containsOnlyKeys(Figure.TEN, Figure.JACK, Figure.QUEEN, Figure.KING, Figure.ACE);
        assertTrue(resultGroupedByFigure.values().stream()
                .allMatch(cardsOfTheSameFigure -> cardsOfTheSameFigure.size() == 1));
    }
}
