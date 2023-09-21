package deny.poker.poc.game;

import deny.poker.poc.finder.HighestSetFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleGameManager {
    private final HighestSetFinder highestSetFinder;
    private final Game game;

    @Autowired
    public ConsoleGameManager(HighestSetFinder highestSetFinder) {
        this.highestSetFinder = highestSetFinder;
        this.game = new Game(5);
        start();
    }

    void start() {
        while(game.isEnd()) {
            var player = game.nextPlayer();
            System.out.println(player);
            //todo readkey for action
            RoundOption roundOption = null;
            switch (roundOption) {
                case RISE -> System.out.println("To what");
                case MAT -> System.out.println("Check recently given set and check if its highest");
                case CHECK -> System.out.println("Check if recently given set is available");
            }
        }
    }
}
