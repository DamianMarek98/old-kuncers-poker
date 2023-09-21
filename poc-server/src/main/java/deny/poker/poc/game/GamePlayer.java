package deny.poker.poc.game;

import deny.poker.poc.Card;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GamePlayer {
    private final UUID id = UUID.randomUUID();
    private int numOfCards = 1;
    private final String name;
    private Set<Card> hand;

    GamePlayer(String name) {
        this.name = name;
    }

    UUID getId() {
        return id;
    }

    int getNumOfCards() {
        return numOfCards;
    }

    void setNumOfCards(int numOfCards) {
        this.numOfCards = numOfCards;
    }

    boolean hasEnoughCards() {
        return numOfCards == hand.size();
    }

    void clearHand() {
        hand = new HashSet<>();
    }

    void giveCard(Card card) {
        hand.add(card);
    }

    Set<Card> getHand() {
        return hand;
    }
}
