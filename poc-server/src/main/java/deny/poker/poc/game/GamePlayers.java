package deny.poker.poc.game;

import java.util.List;

final class GamePlayers {
    private final List<GamePlayer> players;
    private int currentPlayerIndex = 0;

    GamePlayers(List<GamePlayer> players) {
        this.players = players.stream().toList();
    }
    void nextPlayer() {
        do {
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) {
                currentPlayerIndex = 0;
            }
        } while (players.get(currentPlayerIndex).isEliminated());
    }

    void previousPlayer() {
        do {
            if (currentPlayerIndex == 0) {
                currentPlayerIndex = players.size() - 1;
            } else {
                currentPlayerIndex--;
            }
        } while (players.get(currentPlayerIndex).isEliminated());
    }

    GamePlayer getPreviousPlayer() {
        var playerIndex = currentPlayerIndex;

        do {
            if (playerIndex == 0) {
                playerIndex = players.size() - 1;
            } else {
                playerIndex--;
            }
        } while (players.get(playerIndex).isEliminated());
        return players.get(playerIndex);
    }

    GamePlayer getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    boolean isOnlyOnePlayerPlayingLeft() {
       return players.stream().filter(GamePlayer::isPlaying).count() > 1;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "GamePlayers{" +
                "players=" + players +
                ", currentPlayerIndex=" + currentPlayerIndex +
                '}';
    }
}
