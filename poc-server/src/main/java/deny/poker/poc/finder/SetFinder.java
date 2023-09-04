package deny.poker.poc.finder;

import deny.poker.poc.Card;

import java.util.List;
import java.util.Optional;

interface SetFinder {
    Optional<List<Card>> find(List<Card> cards);
}
