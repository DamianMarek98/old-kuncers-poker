package deny.poker.poc;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck {
    public final List<Card> cards;

    public Deck() {
        this.cards = generateDeck();
    }

    private List<Card> generateDeck() {
        return Arrays.stream(Figure.values())
                .map(figure -> Arrays.stream(Color.values())
                        .map(color -> new Card(color, figure))
                        .toList())
                .flatMap(Collection::stream)
                .toList();
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }
}
