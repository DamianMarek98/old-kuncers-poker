package deny.poker.poc.game;

import deny.poker.poc.Card;
import deny.poker.poc.CardsSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
    private final UUID id = UUID.randomUUID();
    private final GamePlayers gamePlayers;
    private CardsSet currentCardsSet;

    public void setCurrentCardsSet(CardsSet currentCardsSet) {
        this.currentCardsSet = currentCardsSet;
    }

    public CardsSet getCurrentCardsSet() {
        return currentCardsSet;
    }

    Game(int numberOfPlayer) {
        final List<GamePlayer> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayer; i++) {
            players.add(new GamePlayer("Player num:" + i));
        }
        //todo should be random, or list should be shuffled
        gamePlayers = new GamePlayers(players);
    }


    boolean isEnd() {
        return gamePlayers.isOnlyOnePlayerPlayingLeft();
    }

    List<GamePlayer> getPlayers() {
        return gamePlayers.getPlayers();
    }

    public void rise(CardsSet cardsSet) {
        setCurrentCardsSet(cardsSet);
        gamePlayers.nextPlayer();
    }

    public List<Card> getCardsInPlay() {
        return gamePlayers.getPlayers().stream()
                .flatMap(gamePlayer -> gamePlayer.getHand().stream())
                .toList();
    }

    public void drawCardForPreviousPlayer() {
        var previousPlayer = gamePlayers.getPreviousPlayer();
        previousPlayer.setNumOfCards(previousPlayer.getNumOfCards() + 1);
        if (previousPlayer.isPlaying()) {
            gamePlayers.previousPlayer();
        }
        currentCardsSet = null;
    }

    public void drawCardForCurrentPlayer() {
        var currentPlayer = gamePlayers.getCurrentPlayer();
        currentPlayer.setNumOfCards(currentPlayer.getNumOfCards() + 1);
        if (currentPlayer.isEliminated()) {
            gamePlayers.nextPlayer();
        }
        currentCardsSet = null;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", players=" + gamePlayers +
                ", currentCardsSet=" + currentCardsSet +
                '}';
    }
}
