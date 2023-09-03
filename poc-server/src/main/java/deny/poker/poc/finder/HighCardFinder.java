package deny.poker.poc.finder;

import deny.poker.poc.Card;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
class HighCardFinder implements SetFinder {
    @Override
    public Optional<List<Card>> find(List<Card> cards) {
        return cards.stream()
                .max(Comparator.comparing(card -> card.figure().getValue()))
                .map(List::of);
    }
}
