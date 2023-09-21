package deny.poker.poc.game;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;

import java.util.*;

class CardBroker {
    private final List<Card> deck = new ArrayList<>();
    private final int numberOfAvailableCards;
    private final Random random = new Random();

    public CardBroker() {
        int counter = 0;
        for (var color : Color.values()) {
            for (var figure : Figure.values()) {
                deck.add(new Card(color, figure));
                counter++;
            }
        }
        numberOfAvailableCards = counter;
    }

    void dealCards(List<GamePlayer> players) {
        var givenCards = new HashSet<Card>();
        players.forEach(GamePlayer::clearHand);
        while (!players.stream().allMatch(GamePlayer::hasEnoughCards)) {
            players.forEach(player -> {
                if (!player.hasEnoughCards()) {
                    Card cardToDeal;
                    do {
                        cardToDeal = deck.get(random.nextInt(0, numberOfAvailableCards));
                    } while (givenCards.contains(cardToDeal));
                    player.giveCard(cardToDeal);
                    givenCards.add(cardToDeal);
                }
            });
        }
    }

}
