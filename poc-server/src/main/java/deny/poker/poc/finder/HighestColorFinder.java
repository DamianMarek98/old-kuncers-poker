package deny.poker.poc.finder;

import deny.poker.poc.Card;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class HighestColorFinder implements SetFinder {

    private static final int NEEDED_AMOUNT = 5;

    @Override
    public Optional<List<Card>> find(List<Card> cards) {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::color))
                .entrySet()
                .stream()
                .filter((colourCardsEntry -> colourCardsEntry.getValue().size() >= NEEDED_AMOUNT))
                .max(Comparator.comparing(colorCardsEntry -> colorCardsEntry.getKey().getValue()))
                .map(Map.Entry::getValue);
    }
}
