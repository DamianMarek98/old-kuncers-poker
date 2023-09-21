package deny.poker.poc.game;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;

import java.util.*;

class Game {
    private final UUID id = UUID.randomUUID();
    private final List<GamePlayer> players = new ArrayList<>();
    private Integer currentPlayerIndex;

    Game(int numberOfPlayer) {
        for (int i = 0; i < numberOfPlayer; i++) {
            players.add(new GamePlayer("Player num:" + i));
        }
        //todo should be random
        currentPlayerIndex = 0;
    }

    GamePlayer nextPlayer() {
        if (currentPlayerIndex == null) {
            currentPlayerIndex = 0;
            return players.get(currentPlayerIndex);
        }
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) {
            currentPlayerIndex = 0;
        }
        return players.get(currentPlayerIndex);
    }



    boolean isEnd() {
        return players.size() == 1;
    }

    List<GamePlayer> getPlayers() {
        return players;
    }
}
