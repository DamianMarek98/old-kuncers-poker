package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.SetRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HighestSetFinder implements SetFinder {
    private final EnumMap<SetRank, SetFinder> setFinderMap = new EnumMap<>(SetRank.class);

    @Autowired
    HighestSetFinder(HighCardFinder highCardFinder,
                     HighestPairFinder highestPairFinder,
                     HighestTwoPairsFinder highestTwoPairsFinder,
                     HighestStraightFinder highestStraightFinder,
                     HighestTrioFinder highestTrioFinder,
                     HighestFullHouseFinder highestFullHouseFinder,
                     HighestFourOfKindFinder highestFourOfKindFinder,
                     HighestColorFinder highestColorFinder,
                     HighestPokerFinder highestPokerFinder) {
        setFinderMap.put(SetRank.HIGH_CARD, highCardFinder);
        setFinderMap.put(SetRank.PAIR, highestPairFinder);
        setFinderMap.put(SetRank.TWO_PAIRS, highestTwoPairsFinder);
        setFinderMap.put(SetRank.STRAIGHT, highestStraightFinder);
        setFinderMap.put(SetRank.THREE_OF_A_KIND, highestTrioFinder);
        setFinderMap.put(SetRank.FULL_HOUSE, highestFullHouseFinder);
        setFinderMap.put(SetRank.FOUR_OF_A_KIND, highestFourOfKindFinder);
        setFinderMap.put(SetRank.COLOR, highestColorFinder);
        setFinderMap.put(SetRank.POKER, highestPokerFinder);
    }

    @Override
    public Optional<List<Card>> find(List<Card> cards) {
        return Arrays.stream(SetRank.values())
                .sorted(Comparator.comparingInt(SetRank::getRank))
                .sorted(Comparator.reverseOrder())
                .map(setRank -> setFinderMap.get(setRank).find(cards))
                .filter(Optional::isPresent)
                .findFirst()
                .orElse(Optional.empty());
    }
}
