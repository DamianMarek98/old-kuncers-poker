package deny.poker.poc.finder;

import deny.poker.poc.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class HighestTwoPairsFinder implements SetFinder {

    private final HighestPairFinder highestPairFinder;

    public HighestTwoPairsFinder(@Autowired HighestPairFinder highestPairFinder) {
        this.highestPairFinder = highestPairFinder;
    }

    @Override
    public Optional<List<Card>> find(List<Card> cards) {
        var cardsInPlay = new ArrayList<>(cards);
        var highestPair = highestPairFinder.find(cardsInPlay);
        if (highestPair.isEmpty()) {
            return Optional.empty();
        }
        cardsInPlay.removeAll(highestPair.get());
        var secondHighestPair = highestPairFinder.find(cardsInPlay);
        if (secondHighestPair.isEmpty()) {
            return Optional.empty();
        }
        var pairs = highestPair.get();
        pairs.addAll(secondHighestPair.get());
        return Optional.of(pairs);
    }
}
