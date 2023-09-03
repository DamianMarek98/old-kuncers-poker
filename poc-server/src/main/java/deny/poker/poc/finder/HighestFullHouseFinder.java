package deny.poker.poc.finder;

import deny.poker.poc.Card;
import deny.poker.poc.Figure;
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
        var trio = highestTrioFinder.find(cards);
        if (trio.isEmpty()) {
            return Optional.empty();
        }
        final Figure trioFigure = trio.get().get(0).figure();
        var cardsToFindPair = cards.stream()
                .filter(card -> !card.figure().equals(trioFigure))
                .toList();
        var pair = highestPairFinder.find(cardsToFindPair);
        if (pair.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(Stream.of(pair, trio)
                .map(Optional::get)
                .flatMap(Collection::stream)
                .toList());
    }
}
