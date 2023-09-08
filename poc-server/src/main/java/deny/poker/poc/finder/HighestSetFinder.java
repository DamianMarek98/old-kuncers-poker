package deny.poker.poc.finder;

import deny.poker.poc.Card;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HighestSetFinder implements SetFinder {
    @Override
    public Optional<List<Card>> find(List<Card> cards) {
        return Optional.empty();
    }
}
