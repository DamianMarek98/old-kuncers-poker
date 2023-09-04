package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
class HighestPokerFinder implements SetFinder {

    private final HighestStraightFinder highestStraightFinder;

    @Autowired
    public HighestPokerFinder(HighestStraightFinder highestStraightFinder) {
        this.highestStraightFinder = highestStraightFinder;
    }

    @Override
    public Optional<List<Card>> find(List<Card> cards) {
        var colorCardsMap = cards.stream()
                .collect(Collectors.groupingBy(Card::color));
        Map<Color, List<Card>> smallPokers = new EnumMap<>(Color.class);
        Map<Color, List<Card>> bigPokers = new EnumMap<>(Color.class);
        fillPokers(colorCardsMap, smallPokers, bigPokers);

        if (bigPokers.isEmpty() && smallPokers.isEmpty()) {
            return Optional.empty();
        } else if (bigPokers.isEmpty()) {
            return findPokerWithHighestColor(smallPokers);
        }

        return findPokerWithHighestColor(bigPokers);
    }

    private void fillPokers(Map<Color, List<Card>> colorCardsMap, Map<Color, List<Card>> smallPokers, Map<Color, List<Card>> bigPokers) {
        for (var colorCardsEntry : colorCardsMap.entrySet()) {
            highestStraightFinder.find(colorCardsEntry.getValue())
                    .ifPresent(poker -> {
                        var isBigPoker = poker.stream()
                                .anyMatch(card -> card.figure().equals(Figure.ACE));
                        if (isBigPoker) {
                            bigPokers.put(colorCardsEntry.getKey(), poker);
                        } else {
                            smallPokers.put(colorCardsEntry.getKey(), poker);
                        }
                    });
        }
    }

    private Optional<List<Card>> findPokerWithHighestColor(Map<Color, List<Card>> pokers) {
        return pokers.entrySet()
                .stream()
                .max(Comparator.comparingInt(entry -> entry.getKey().getValue()))
                .map(Map.Entry::getValue);
    }
}
