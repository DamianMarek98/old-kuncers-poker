package deny.poker.poc.game.web;

import deny.poker.poc.game.GameService;
import deny.poker.poc.game.web.request.RiseToHighCardRequest;
import deny.poker.poc.game.web.request.StartGameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startGame(@RequestBody StartGameRequest startGameRequest) {
        gameService.startGame(startGameRequest.numberOfPlayers());
        return ResponseEntity.ok(gameService.getCurrentGame().toString());
    }

    @PostMapping("/rise-to-high-card")
    public ResponseEntity<String> decision(@RequestBody RiseToHighCardRequest riseToHighCardRequest) {
        gameService.riseToHighCard(riseToHighCardRequest.figure());
        return ResponseEntity.ok(gameService.getCurrentGame().toString());
    }

    @PostMapping("/check")
    public ResponseEntity<Void> check() {
        gameService.check();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<String> getGameState() {
        return ResponseEntity.ok(gameService.getCurrentGame().toString());
    }
}
