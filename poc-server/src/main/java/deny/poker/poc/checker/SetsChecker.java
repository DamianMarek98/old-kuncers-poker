package deny.poker.poc.checker;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import deny.poker.poc.game.check.CardsSetPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetsChecker implements CardsSetPresent {
    private final HighCardChecker highCardChecker;
    private final PairChecker pairChecker;
    private final StraightChecker straightChecker;
    private final TrioChecker trioChecker;
    private final FourOfAKindChecker fourOfAKindChecker;
    private final ColorChecker colorChecker;
    private final PokerChecker pokerChecker;

    @Autowired
    SetsChecker(HighCardChecker highCardChecker,
                       PairChecker pairChecker,
                       StraightChecker straightChecker,
                       TrioChecker trioChecker,
                       FourOfAKindChecker fourOfAKindChecker,
                       ColorChecker colorChecker,
                       PokerChecker pokerChecker) {
        this.highCardChecker = highCardChecker;
        this.pairChecker = pairChecker;
        this.straightChecker = straightChecker;
        this.trioChecker = trioChecker;
        this.fourOfAKindChecker = fourOfAKindChecker;
        this.colorChecker = colorChecker;
        this.pokerChecker = pokerChecker;
    }

    @Override
    public boolean checkHighCard(List<Card> cards, Figure figure) {
        return highCardChecker.contains(cards, figure, null);
    }

    @Override
    public boolean checkPair(List<Card> cards, Figure pairFigure) {
        return pairChecker.contains(cards, pairFigure, null);
    }

    @Override
    public boolean checkTwoPairs(List<Card> cards, Figure pairFigure1, Figure pairFigure2) {
        return pairChecker.contains(cards, pairFigure1, null) && pairChecker.contains(cards, pairFigure2, null);
    }

    @Override
    public boolean checkStraight(List<Card> cards, Figure from) {
        return straightChecker.contains(cards, from, null);
    }

    @Override
    public boolean checkThreeOfAKind(List<Card> cards, Figure figure) {
        return trioChecker.contains(cards, figure, null);
    }

    @Override
    public boolean checkFullHouse(List<Card> cards, Figure figureTrio, Figure figurePair) {
        return trioChecker.contains(cards, figureTrio, null) && pairChecker.contains(cards, figurePair, null);
    }

    @Override
    public boolean checkFourOfAKind(List<Card> cards, Figure figure) {
        return fourOfAKindChecker.contains(cards, figure, null);
    }

    @Override
    public boolean checkColor(List<Card> cards, Color color) {
        return colorChecker.contains(cards, null, color);
    }

    @Override
    public boolean checkPoker(List<Card> cards, Figure from, Color color) {
        return pokerChecker.contains(cards, from, color);
    }
}
