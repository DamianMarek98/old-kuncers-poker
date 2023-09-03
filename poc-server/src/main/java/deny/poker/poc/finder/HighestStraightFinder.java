package deny.poker.poc.finder;

import deny.poker.poc.Card;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
class HighestStraightFinder implements SetFinder {
    @Override
    public Optional<List<Card>> find(List<Card> cards) {
        if (cards.isEmpty()) {
            return Optional.empty();
        }
        var sortedCards = cards.stream()
                .sorted(Comparator.comparing(Card::figure).reversed())
                .toList();
        var lastCard = sortedCards.get(0);
        var potentialStraight = new Card[5];
        potentialStraight[0] = lastCard;
        var potentialStraightIndex = 1;
        for (int i = 1; i < sortedCards.size(); i++) {
            var currentCard = sortedCards.get(i);
            final int currentCardValue = lastCard.figure().getValue();
            final int lastCardValue = currentCard.figure().getValue();
            if (currentCardValue - lastCardValue == 1) {
                potentialStraight[potentialStraightIndex] = currentCard;
                lastCard = currentCard;
                potentialStraightIndex++;
                if (potentialStraightIndex == 5) {
                    return Optional.of(List.of(potentialStraight));
                }
            } else if (currentCardValue - lastCardValue > 1) {
                potentialStraight[0] = currentCard;
                potentialStraightIndex = 1;
            }
        }


        return Optional.empty();
    }
}
