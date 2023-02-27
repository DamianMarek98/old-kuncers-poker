package deny.poker.poc;

import java.util.List;
import java.util.Optional;

public interface CardLayout {
    String getName();

    int getValue();

    List<Integer> neededAmounts();

    boolean fiveInTheSameColor();
    Optional<List<Card>> findHighest(List<Card> availableCards);
}
