package deny.poker.poc.finder;

import deny.poker.poc.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
class HighestFullHouseFinder implements SetFinder {
    private final HighestTrioFinder highestTrioFinder;
    private final HighestPairFinder highestPairFinder;

    @Autowired
    public HighestFullHouseFinder(HighestTrioFinder highestTrioFinder, HighestPairFinder highestPairFinder) {
        this.highestTrioFinder = highestTrioFinder;
        this.highestPairFinder = highestPairFinder;
    }

    @Override
    public Optional<List<Card>> find(List<Card> cards) {
        var pair = highestPairFinder.find(cards);
        if (pair.isEmpty()) {
            return Optional.empty();
        }
        //todo remove figure of pair
        var trio = highestTrioFinder.find(cards);
        if (trio.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(Stream.of(pair, trio)
                .map(Optional::get)
                .flatMap(Collection::stream)
                .toList());
    }
}
