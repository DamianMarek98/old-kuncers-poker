package deny.poker.poc.game;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardBrokerTest {
    private final CardBroker cardBroker = new CardBroker();

    @Test
    void shouldDealOneUniqueCardForEachPlayer() {
        var game = new Game(5);
        cardBroker.dealCards(game.getPlayers());
        game.getPlayers().forEach(player -> assertTrue(player.hasEnoughCards()));
        var cardsInGame =  game.getPlayers().stream()
                .flatMap(player -> player.getHand().stream())
                .collect(Collectors.toSet());
        assertThat(cardsInGame.size()).isEqualTo(game.getPlayers().stream()
                .map(GamePlayer::getNumOfCards)
                .reduce(Integer::sum)
                .orElse(0));
    }
}
