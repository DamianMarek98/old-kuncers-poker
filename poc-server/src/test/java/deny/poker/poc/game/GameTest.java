package deny.poker.poc.game;

import deny.poker.poc.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class GameTest {
    @Test
    void constructorShouldCreateGameWithGivenNumberOfPlayers() {
        int numberOfPlayers = 4;
        var game = new Game(numberOfPlayers);
        assertThat(game.getPlayers().size()).isEqualTo(numberOfPlayers);
    }

    @Test
    void riseShouldSetCurrentCardsSet() {
        var game = new Game(4);
        CardsSet cardsSet = new CardsSet(SetRank.HIGH_CARD, Figure.ACE, null, null);
        game.rise(cardsSet);
        assertThat(game.getCurrentCardsSet()).isEqualTo(cardsSet);
    }

    @Test
    void getCardsInPlayShouldReturnAllCardsInPlay() {
        Card card1 = new Card(Color.RED_HEART, Figure.ACE);
        Card card2 = new Card(Color.BLACK_CLUB, Figure.JACK);
        var game = new Game(2);
        game.getPlayers().get(0).giveCard(card1);
        game.getPlayers().get(1).giveCard(card2);
        assertThat(game.getCardsInPlay()).containsExactlyInAnyOrder(card1, card2);
    }

    @Test
    void drawCardForPreviousPlayerShouldSetCurrentCardsSetToNull() {
        var game = new Game(4);
        game.drawCardForPreviousPlayer();
        assertNull(game.getCurrentCardsSet());
    }

    @Test
    void drawCardForPreviousPlayerShouldIncrementCardsCounterForAPreviousPlayer() {
        var game = new Game(4);
        game.drawCardForPreviousPlayer();
        assertThat(game.getPlayers().get(3).getNumOfCards()).isEqualTo(2);
    }

    @Test
    void drawCardForPreviousPlayerShouldChangeCurrentPlayerIndexToPreviousPlayerWhenHeIsStillPlaying() {
        var game = new Game(4);
        game.drawCardForPreviousPlayer();
        game.drawCardForCurrentPlayer();
        assertThat(game.getPlayers().get(3).getNumOfCards()).isEqualTo(3);
    }

    @Test
    void drawCardForCurrentPlayerShouldSetCurrentCardsSetToNull() {
        var game = new Game(4);
        game.drawCardForCurrentPlayer();
        assertNull(game.getCurrentCardsSet());
    }

    @Test
    void drawCardForCurrentPlayerShouldIncrementCardsCounterForACurrentPlayer() {
        var game = new Game(4);
        game.drawCardForCurrentPlayer();
        assertThat(game.getPlayers().get(0).getNumOfCards()).isEqualTo(2);
    }

    @Test
    void drawCardForCurrentPlayerShouldChangeCurrentPlayerIndexToNextPlayerWhenCurrentPlayerIsEliminated() {
        var game = new Game(4);
        game.getPlayers().get(0).setNumOfCards(4);
        game.drawCardForCurrentPlayer();
        game.drawCardForCurrentPlayer();
        assertThat(game.getPlayers().get(0).getNumOfCards()).isEqualTo(5);
        assertThat(game.getPlayers().get(1).getNumOfCards()).isEqualTo(2);
    }
}
