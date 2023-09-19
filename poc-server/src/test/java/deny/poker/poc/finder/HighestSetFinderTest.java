package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HighestSetFinderTest {
    @Autowired
    private HighestSetFinder highestSetFinder;

    @Test
    void givenSetOfCardsWithNineTenAndQueenShouldReturnQueen() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var cards = List.of(nine, ten, queen);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> assertThat(set).containsExactlyInAnyOrder(queen),
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithTwoNinesOneTenAndOneQueenShouldReturnPairOfNines() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var cards = List.of(nine, nine2, ten, queen);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> assertThat(set).containsExactlyInAnyOrder(nine, nine2),
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithTwoNinesTwoAcesOneTenAndOneQueenShouldReturnPairOfAcesAndNines() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var ace = new Card(Color.BLACK_CLUB, Figure.ACE);
        var ace2 = new Card(Color.RED_DIAMOND, Figure.ACE);
        var cards = List.of(nine, nine2, ten, queen, ace, ace2);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> assertThat(set).containsExactlyInAnyOrder(ace, ace2, nine, nine2),
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithTwoNinesOneTenOneJackOneQueenAndOneKingShouldReturnSmallStraight() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var cards = List.of(nine, nine2, ten, jack, queen, king);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> {
                    assertThat(set).hasSize(5);
                    assertThat(set).containsAnyElementsOf(List.of(nine, nine2, king, queen, jack, ten));
                },
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithTwoNinesTwoAcesOneTenOneJackOneQueenAndOneKingShouldReturnBigStraight() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var ace = new Card(Color.BLACK_CLUB, Figure.ACE);
        var ace2 = new Card(Color.RED_DIAMOND, Figure.ACE);
        var cards = List.of(nine, nine2, ten, jack, queen, king, ace, ace2);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> {
                    assertThat(set).hasSize(5);
                    assertThat(set).containsAnyElementsOf(List.of(ace, ace2, king, queen, jack, ten));
                },
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithThreeNinesOneTenOneJackOneQueenAndOneKingShouldReturnThreeNines() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var nine3 = new Card(Color.BLACK_SPADE, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var cards = List.of(nine, nine2, nine3, ten, jack, queen, king);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> assertThat(set).containsAnyElementsOf(List.of(nine, nine2, nine3)),
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithThreeNinesOneTenOneJackOneQueenAndTwoKingsShouldReturnFullHouseNinesOnKings() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var nine3 = new Card(Color.BLACK_SPADE, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var king2 = new Card(Color.RED_DIAMOND, Figure.KING);
        var cards = List.of(nine, nine2, nine3, ten, jack, queen, king, king2);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> assertThat(set).containsAnyElementsOf(List.of(nine, nine2, nine3, king, king2)),
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithThreeNinesOneTenOneJackOneQueenAndThreeKingsShouldReturnFullHouseKingsOnNines() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var nine3 = new Card(Color.BLACK_SPADE, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var king2 = new Card(Color.RED_DIAMOND, Figure.KING);
        var king3 = new Card(Color.BLACK_CLUB, Figure.KING);
        var cards = List.of(nine, nine2, nine3, ten, jack, queen, king, king2, king3);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> {
                    assertThat(set).hasSize(5);
                    assertThat(set).containsAnyElementsOf(List.of(nine, nine2, nine3, king, king2, king3));
                    var groupedSet = set.stream().collect(Collectors.groupingBy(Card::figure));
                    assertThat(groupedSet.get(Figure.KING)).hasSize(3);
                    assertThat(groupedSet.get(Figure.NINE)).hasSize(2);
                },
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithFourNinesOneTenOneJackOneQueenAndOneKingShouldReturnFourNines() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var nine3 = new Card(Color.BLACK_SPADE, Figure.NINE);
        var nine4 = new Card(Color.RED_HEART, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var cards = List.of(nine, nine2, nine3, nine4, ten, jack, queen, king);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> assertThat(set).containsAnyElementsOf(List.of(nine, nine2, nine3, nine4)),
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithFiveRedDiamondsAndNoPokerShouldReturnColorRedDiamond() {
        //given
        var nine = new Card(Color.BLACK_CLUB, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var ten = new Card(Color.RED_DIAMOND, Figure.TEN);
        var jack = new Card(Color.RED_DIAMOND, Figure.JACK);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var ace = new Card(Color.RED_DIAMOND, Figure.ACE);
        var cards = List.of(nine, nine2, ten, jack, queen, king, ace);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> {
                    assertThat(set).hasSize(5);
                    assertTrue(set.stream().allMatch(card -> card.color().equals(Color.RED_DIAMOND)));
                },
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithFiveRedDiamondsAndFiveBlackSpaceAndNoPokerShouldReturnColorRedDiamond() {
        //given
        var nine = new Card(Color.BLACK_SPADE, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var ten = new Card(Color.RED_DIAMOND, Figure.TEN);
        var jack = new Card(Color.RED_DIAMOND, Figure.JACK);
        var jack2 = new Card(Color.BLACK_SPADE, Figure.JACK);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var queen2 = new Card(Color.BLACK_SPADE, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var ace2 = new Card(Color.RED_DIAMOND, Figure.ACE);
        var ace = new Card(Color.BLACK_SPADE, Figure.ACE);
        var cards = List.of(nine, nine2, ten, jack, jack2, queen, queen2, king, ace2, ace);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> {
                    assertThat(set).hasSize(5);
                    assertTrue(set.stream().allMatch(card -> card.color().equals(Color.BLACK_SPADE)));
                },
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithSmallPokerRedDiamondsAndFiveBlackSpadeButNoPokerShouldReturnSmallPokerRedDiamonds() {
        //given
        var nine = new Card(Color.BLACK_SPADE, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var ten = new Card(Color.RED_DIAMOND, Figure.TEN);
        var jack = new Card(Color.RED_DIAMOND, Figure.JACK);
        var jack2 = new Card(Color.BLACK_SPADE, Figure.JACK);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var queen2 = new Card(Color.BLACK_SPADE, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var king2 = new Card(Color.RED_DIAMOND, Figure.KING);
        var ace = new Card(Color.BLACK_SPADE, Figure.ACE);
        var cards = List.of(nine, nine2, ten, jack, jack2, queen, queen2, king, king2, ace);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> {
                    assertThat(set).containsExactlyInAnyOrder(nine2, ten, jack, queen, king2);
                    assertTrue(set.stream().allMatch(card -> card.color().equals(Color.RED_DIAMOND)));
                },
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithSmallPokerRedDiamondsAndSmallBlackSpadePokerShouldReturnSmallBlackSpadePoker() {
        //given
        var nine = new Card(Color.BLACK_SPADE, Figure.NINE);
        var nine2 = new Card(Color.RED_DIAMOND, Figure.NINE);
        var ten = new Card(Color.RED_DIAMOND, Figure.TEN);
        var jack = new Card(Color.RED_DIAMOND, Figure.JACK);
        var jack2 = new Card(Color.BLACK_SPADE, Figure.JACK);
        var queen = new Card(Color.RED_DIAMOND, Figure.QUEEN);
        var queen2 = new Card(Color.BLACK_SPADE, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var king2 = new Card(Color.RED_DIAMOND, Figure.KING);
        var ten2 = new Card(Color.BLACK_SPADE, Figure.TEN);
        var cards = List.of(nine, nine2, ten, jack, jack2, queen, queen2, king, king2, ten2);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> {
                    assertThat(set).containsExactlyInAnyOrder(nine, ten2, jack2, queen2, king);
                    assertTrue(set.stream().allMatch(card -> card.color().equals(Color.BLACK_SPADE)));
                },
                Assertions::fail);
    }

    @Test
    void givenSetOfCardsWithBigPokerRedHeartsAndBigBlackSpadePokerShouldReturnBigBlackSpadePoker() {
        //given
        var nine = new Card(Color.BLACK_SPADE, Figure.NINE);
        var nine2 = new Card(Color.RED_HEART, Figure.NINE);
        var ten = new Card(Color.RED_HEART, Figure.TEN);
        var ten2 = new Card(Color.BLACK_SPADE, Figure.TEN);
        var jack = new Card(Color.RED_HEART, Figure.JACK);
        var jack2 = new Card(Color.BLACK_SPADE, Figure.JACK);
        var queen = new Card(Color.RED_HEART, Figure.QUEEN);
        var queen2 = new Card(Color.BLACK_SPADE, Figure.QUEEN);
        var king = new Card(Color.BLACK_SPADE, Figure.KING);
        var king2 = new Card(Color.RED_HEART, Figure.KING);
        var ace1 = new Card(Color.BLACK_SPADE, Figure.ACE);
        var ace2 = new Card(Color.RED_HEART, Figure.ACE);
        var cards = List.of(nine, nine2, ten, ten2, jack, jack2, queen, queen2, king, king2, ace1, ace2);
        //when
        var result = highestSetFinder.find(cards);
        //then
        result.ifPresentOrElse(
                set -> {
                    assertThat(set).containsExactlyInAnyOrder(ten2, jack2, queen2, king, ace1);
                    assertTrue(set.stream().allMatch(card -> card.color().equals(Color.BLACK_SPADE)));
                },
                Assertions::fail);
    }
}
