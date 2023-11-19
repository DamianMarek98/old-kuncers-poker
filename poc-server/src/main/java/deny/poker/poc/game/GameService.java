package deny.poker.poc.game;

import deny.poker.poc.CardsSet;
import deny.poker.poc.Figure;
import deny.poker.poc.SetRank;
import deny.poker.poc.finder.HighestSetFinder;
import deny.poker.poc.game.events.GameStartedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private Game game;
    private final ApplicationEventPublisher eventPublisher;
    private final HighestSetFinder highestSetFinder;

    @Autowired
    public GameService(ApplicationEventPublisher eventPublisher, HighestSetFinder highestSetFinder) {
        this.eventPublisher = eventPublisher;
        this.highestSetFinder = highestSetFinder;
    }

    public void startGame(int numberOfPlayers) {
        game = new Game(numberOfPlayers);
        eventPublisher.publishEvent(new GameStartedEvent(game));
    }

    public void riseToHighCard(Figure figure) {
        //todo whole validation
        game.rise(new CardsSet(SetRank.HIGH_CARD, figure, null));
    }

    public void check() {
        var cardsSet = game.getCurrentCardsSet();
        var highestSet = highestSetFinder.find(game.getCardsInPlay());
        var highestSetRank = highestSetFinder.findHighestSet(game.getCardsInPlay());
        //todo cardsSet highestSet analysis class etc
        //todo setExistance finder needed/ highest set only needed for MAT
    }

    public Game getCurrentGame() {
        return game;
    }
}
