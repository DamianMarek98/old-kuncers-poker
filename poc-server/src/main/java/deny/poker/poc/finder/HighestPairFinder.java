package deny.poker.poc.finder;

import deny.poker.poc.Card;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
class HighestPairFinder implements SetFinder {
    @Override
    public Optional<List<Card>> find(List<Card> cards) {
        return HighestSetOfOneFigureFinder.findHighestSetOfOneFigure(cards, 2);
    }
}
