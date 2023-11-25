package deny.poker.poc.game.check;

import deny.poker.poc.game.events.CheckingPlayerWonEvent;
import deny.poker.poc.game.events.RoundFinishedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class CheckWonHandler {
    private final ApplicationEventPublisher eventPublisher;

    CheckWonHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public void handleCheckingPlayerWon(CheckingPlayerWonEvent checkingPlayerWonEvent) {
        checkingPlayerWonEvent.game().drawCardForPreviousPlayer();
        eventPublisher.publishEvent(new RoundFinishedEvent(checkingPlayerWonEvent.game()));
    }
}
