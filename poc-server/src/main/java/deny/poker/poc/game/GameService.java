package deny.poker.poc.game;

import deny.poker.poc.CardsSet;
import deny.poker.poc.Figure;
import deny.poker.poc.SetRank;
import deny.poker.poc.finder.HighestSetFinder;
import deny.poker.poc.game.check.CardsSetPresent;
import deny.poker.poc.game.check.CardsSetPresentChecker;
import deny.poker.poc.game.events.CheckingPlayerLostEvent;
import deny.poker.poc.game.events.CheckingPlayerWonEvent;
import deny.poker.poc.game.events.GameStartedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private Game game;
    private final ApplicationEventPublisher eventPublisher;
    private final HighestSetFinder highestSetFinder;
    private final CardsSetPresentChecker cardsSetPresentChecker;

    @Autowired
    public GameService(ApplicationEventPublisher eventPublisher, HighestSetFinder highestSetFinder, CardsSetPresentChecker cardsSetPresentChecker) {
        this.eventPublisher = eventPublisher;
        this.highestSetFinder = highestSetFinder;
        this.cardsSetPresentChecker = cardsSetPresentChecker;
    }

    public void startGame(int numberOfPlayers) {
        game = new Game(numberOfPlayers);
        eventPublisher.publishEvent(new GameStartedEvent(game));
    }

    public void riseToHighCard(Figure figure) {
        //todo whole validation -> next up implement this
        game.rise(new CardsSet(SetRank.HIGH_CARD, figure, null, null));
    }

    public void check() {
        var cardsSet = game.getCurrentCardsSet();
        var cardsInPlay = game.getCardsInPlay();
        if (cardsSetPresentChecker.checkSetPresence(cardsInPlay, cardsSet)) {
            eventPublisher.publishEvent(new CheckingPlayerLostEvent(game));
            return;
        }

        eventPublisher.publishEvent(new CheckingPlayerWonEvent(game));
    }

    public Game getCurrentGame() {
        return game;
    }
}
