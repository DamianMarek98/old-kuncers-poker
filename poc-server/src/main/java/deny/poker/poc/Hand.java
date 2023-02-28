package deny.poker.poc;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public enum Hand implements CardLayout {
    HIGH_CARD("High Card", 0, List.of(1), false, SetsFinder::findHighestCard),
    PAIR("Pair", 1, List.of(2), false, SetsFinder::findHighestPair),
    TWO_PAIRS("Two Pairs", 2, List.of(2, 2), false, SetsFinder::findTwoHighestPairs),
    SMALL_STRAIGHT("Small Straight", 3, List.of(), false, cards -> Optional.empty()),
    HUGE_STRAIGHT("Huge Straight", 4, List.of(), false, cards -> Optional.empty()),
    THREE_OF_KIND("Three of Kind", 5, List.of(3), false, cards -> Optional.empty()),
    FULL("Full", 6, List.of(3, 2), false, cards -> Optional.empty()),
    COLOR("Color", 7, List.of(), true, cards -> Optional.empty()),
    FOUR_OF_KIND("Four of Kind", 8, List.of(4), false, cards -> Optional.empty()),
    POKER("Poker", 9, List.of(), true, cards -> Optional.empty());

    Hand(String name, int value, List<Integer> neededAmounts, boolean fiveCardsInTheSameColor, Function<List<Card>, Optional<List<Card>>> findHighest) {
        this.name = name;
        this.value = value;
        this.neededAmounts = neededAmounts;
        this.fiveCardsInTheSameColor = fiveCardsInTheSameColor;
        this.findHighest = findHighest;
    }

    private final String name;
    private final int value;
    private final List<Integer> neededAmounts;
    private final boolean fiveCardsInTheSameColor;
    private final Function<List<Card>, Optional<List<Card>>> findHighest;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public List<Integer> neededAmounts() {
        return neededAmounts;
    }

    @Override
    public boolean fiveInTheSameColor() {
        return fiveCardsInTheSameColor;
    }

    @Override
    public Optional<List<Card>> findHighest(List<Card> availableCards) {
        return findHighest.apply(availableCards);
    }
}
