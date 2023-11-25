package deny.poker.poc.game.check;

import deny.poker.poc.game.events.CheckingPlayerLostEvent;
import deny.poker.poc.game.events.GameStartedEvent;
import deny.poker.poc.game.events.RoundFinishedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class CheckLostHandler {
    private final ApplicationEventPublisher eventPublisher;

    CheckLostHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public void handleCheckingPlayerLost(CheckingPlayerLostEvent checkingPlayerLostEvent) {
        checkingPlayerLostEvent.game().drawCardForCurrentPlayer();
        eventPublisher.publishEvent(new RoundFinishedEvent(checkingPlayerLostEvent.game()));
    }
}
