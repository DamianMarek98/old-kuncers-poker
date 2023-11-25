package deny.poker.poc.game;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GamePlayerTest {

    @Test
    void getNumOfCardsShouldReturnOneWhenPlayerJustCreated() {
        assertThat(new GamePlayer("test").getNumOfCards()).isEqualTo(1);
    }

    @Test
    void getNumOfCardsShouldReturnTwoWhenPlayerCardNumberSetToTwo() {
        GamePlayer player = new GamePlayer("test");
        player.setNumOfCards(2);
        assertThat(player.getNumOfCards()).isEqualTo(2);
    }

    @Test
    void hasEnoughCardsShouldReturnFalseWhenPlayerHasNoCards() {
        GamePlayer player = new GamePlayer("test");
        assertFalse(player.hasEnoughCards());
    }

    @Test
    void hasEnoughCardsShouldReturnTrueWhenPlayerHasEnoughCards() {
        GamePlayer player = new GamePlayer("test");
        player.giveCard(new Card(Color.RED_HEART, Figure.ACE));
        assertTrue(player.hasEnoughCards());
    }

    @Test
    void hasEnoughCardsShouldReturnTrueWhenPlayerIsEliminated() {
        GamePlayer player = new GamePlayer("test");
        player.setNumOfCards(5);
        assertTrue(player.hasEnoughCards());
    }

    @Test
    void playerHandShouldBeEmptyAfterClearHand() {
        GamePlayer player = new GamePlayer("test");
        player.giveCard(new Card(Color.RED_HEART, Figure.ACE));
        assertFalse(player.getHand().isEmpty());
        player.clearHand();
        assertTrue(player.getHand().isEmpty());
    }

    @Test
    void playerHandModificationOutsideOfGamePlayerShouldNotChangeState() {
        GamePlayer player = new GamePlayer("test");
        player.giveCard(new Card(Color.RED_HEART, Figure.ACE));
        assertFalse(player.getHand().isEmpty());
        player.getHand().clear();
        assertFalse(player.getHand().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void isEliminatedShouldReturnFalseAndIsPlayingShouldReturnTrueWhenNumberOfCardsIsLessThenFive(int numberOfCards) {
        GamePlayer player = new GamePlayer("test");
        player.setNumOfCards(numberOfCards);
        assertFalse(player.isEliminated());
        assertTrue(player.isPlaying());
    }

    @Test
    void isEliminatedShouldReturnTrueAndIsPlayingShouldReturnFalseWhenNumberOfCardsIsFive() {
        GamePlayer player = new GamePlayer("test");
        player.setNumOfCards(5);
        assertTrue(player.isEliminated());
        assertFalse(player.isPlaying());
    }
}
