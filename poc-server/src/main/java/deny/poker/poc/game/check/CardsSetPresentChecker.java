package deny.poker.poc.game.check;

import deny.poker.poc.Card;
import deny.poker.poc.CardsSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardsSetPresentChecker {
    private final CardsSetPresent cardsSetPresent;

    public CardsSetPresentChecker(CardsSetPresent cardsSetPresent) {
        this.cardsSetPresent = cardsSetPresent;
    }

    public boolean checkSetPresence(List<Card> cards, CardsSet cardsSet) {
        return switch (cardsSet.setRank()) {
            case HIGH_CARD -> cardsSetPresent.checkHighCard(cards, cardsSet.figure1());
            case PAIR -> cardsSetPresent.checkPair(cards, cardsSet.figure1());
            case TWO_PAIRS -> cardsSetPresent.checkTwoPairs(cards, cardsSet.figure1(), cardsSet.figure2());
            case STRAIGHT -> cardsSetPresent.checkStraight(cards, cardsSet.figure1());
            case THREE_OF_A_KIND -> cardsSetPresent.checkThreeOfAKind(cards, cardsSet.figure1());
            case FULL_HOUSE -> cardsSetPresent.checkFullHouse(cards, cardsSet.figure1(), cardsSet.figure2());
            case FOUR_OF_A_KIND -> cardsSetPresent.checkFourOfAKind(cards, cardsSet.figure1());
            case COLOR -> cardsSetPresent.checkColor(cards, cardsSet.color());
            case POKER -> cardsSetPresent.checkPoker(cards, cardsSet.figure1(), cardsSet.color());
        };
    }
}
