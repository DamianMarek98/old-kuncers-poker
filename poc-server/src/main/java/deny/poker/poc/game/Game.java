package deny.poker.poc.game;

import deny.poker.poc.Card;
import deny.poker.poc.CardsSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
    private final UUID id = UUID.randomUUID();
    private final List<GamePlayer> players = new ArrayList<>();
    private Integer currentPlayerIndex;
    private CardsSet currentCardsSet;

    public void setCurrentCardsSet(CardsSet currentCardsSet) {
        this.currentCardsSet = currentCardsSet;
    }

    public CardsSet getCurrentCardsSet() {
        return currentCardsSet;
    }

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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", players=" + players +
                ", currentPlayerIndex=" + currentPlayerIndex +
                ", currentCardsSet=" + currentCardsSet +
                '}';
    }

    public void rise(CardsSet cardsSet) {
        currentCardsSet = cardsSet;
        nextPlayer();
    }

    public List<Card> getCardsInPlay() {
        return players.stream().flatMap(gamePlayer -> gamePlayer.getHand().stream()).toList();
    }
}
