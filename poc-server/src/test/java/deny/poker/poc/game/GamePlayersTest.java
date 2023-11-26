package deny.poker.poc.game;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GamePlayersTest {
    private final List<GamePlayer> players = List.of(new GamePlayer("1"),
            new GamePlayer("2"),
            new GamePlayer("3"),
            new GamePlayer("4"));

    @Test
    void nextPlayerShouldSetCurrentPlayerIndexToSecondPlayer() {
        var gamePlayers = new GamePlayers(players);
        gamePlayers.nextPlayer();
        assertThat(players.indexOf(gamePlayers.getCurrentPlayer())).isEqualTo(1);
    }

    @Test
    void nextPlayerShouldSetCurrentPlayerIndexToFirstPlayerAfterGettingToTheEndOfTheList() {
        var gamePlayers = new GamePlayers(players);
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        assertThat(players.indexOf(gamePlayers.getCurrentPlayer())).isEqualTo(0);
    }

    @Test
    void nextPlayerShouldSetCurrentPlayerIndexToThirdPlayerWhenSecondPlayerIsEliminated() {
        var gamePlayers = new GamePlayers(players);
        gamePlayers.getPlayers().get(1).setNumOfCards(5);
        gamePlayers.nextPlayer();
        assertThat(players.indexOf(gamePlayers.getCurrentPlayer())).isEqualTo(2);
    }

    @Test
    void previousPlayerShouldSetCurrentPlayerIndexToLastWhenCurrentPlayerHas0Index() {
        var gamePlayers = new GamePlayers(players);
        gamePlayers.previousPlayer();
        assertThat(players.indexOf(gamePlayers.getCurrentPlayer())).isEqualTo(3);
    }

    @Test
    void previousPlayerShouldSetCurrentPlayerIndexToSecondWhenCurrentPlayerWasWithIndexThree() {
        var gamePlayers = new GamePlayers(players);
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        gamePlayers.previousPlayer();
        assertThat(players.indexOf(gamePlayers.getCurrentPlayer())).isEqualTo(2);
    }

    @Test
    void previousPlayerShouldSetCurrentPlayerIndexToSecondPlayerWhenThirdPlayerIsEliminated() {
        var gamePlayers = new GamePlayers(players);
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        gamePlayers.getPlayers().get(2).setNumOfCards(5);
        gamePlayers.previousPlayer();
        assertThat(players.indexOf(gamePlayers.getCurrentPlayer())).isEqualTo(1);
    }

    @Test
    void getPreviousPlayerShouldReturnLastPlayerWhenCurrentIndexCurrentPlayerHas0Index() {
        var gamePlayers = new GamePlayers(players);
        assertThat(players.indexOf(gamePlayers.getPreviousPlayer())).isEqualTo(players.size() - 1);
    }

    @Test
    void getPreviousPlayerShouldReturnSecondPlayerWhenCurrentPlayerWasWithIndexThree() {
        var gamePlayers = new GamePlayers(players);
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        assertThat(players.indexOf(gamePlayers.getPreviousPlayer())).isEqualTo(2);
    }

    @Test
    void getPreviousPlayerShouldReturnSecondPlayerWhenThirdPlayerIsEliminated() {
        var gamePlayers = new GamePlayers(players);
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        gamePlayers.nextPlayer();
        gamePlayers.getPlayers().get(2).setNumOfCards(5);
        assertThat(players.indexOf(gamePlayers.getPreviousPlayer())).isEqualTo(1);
    }

    @Test
    void getCurrentPlayerShouldReturnPlayerWithCurrentPlayerIndex() {
        var gamePlayers = new GamePlayers(players);
        assertThat(players.indexOf(gamePlayers.getCurrentPlayer())).isEqualTo(0);
    }

    @Test
    void sOnlyOnePlayerPlayingLeftShouldReturnTrueWhenTwoPlayersArePlaying() {
        var gamePlayers = new GamePlayers(players);
        gamePlayers.getPlayers().get(0).setNumOfCards(5);
        gamePlayers.getPlayers().get(1).setNumOfCards(5);
        assertTrue(gamePlayers.isOnlyOnePlayerPlayingLeft());
    }

    @Test
    void sOnlyOnePlayerPlayingLeftShouldReturnFalseWhenOnePlayersIsPlaying() {
        var gamePlayers = new GamePlayers(players);
        gamePlayers.getPlayers().get(0).setNumOfCards(5);
        gamePlayers.getPlayers().get(1).setNumOfCards(5);
        gamePlayers.getPlayers().get(2).setNumOfCards(5);
        assertFalse(gamePlayers.isOnlyOnePlayerPlayingLeft());
    }
}
