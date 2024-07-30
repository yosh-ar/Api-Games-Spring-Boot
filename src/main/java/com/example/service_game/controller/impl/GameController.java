package com.example.service_game.controller.impl;

import com.example.service_game.controller.GameApi;
import com.example.service_game.controller.commons.entities.Game;
import com.example.service_game.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController  implements GameApi {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<Game> createGame(Game game) {
        Game gameCreated = this.gameService.saveGame(game);
        return ResponseEntity.ok(gameCreated);
    }
    public ResponseEntity<Game> getGame( String id) {
        Game game = this.gameService.getGame(id);
        return ResponseEntity.ok(game);
    }
    @Override
    public ResponseEntity<Game> updateGame(String id, Game game) {
        game.setId(Long.parseLong(id));
        Game gameUpdated = this.gameService.updateGame(game);
        return ResponseEntity.ok(gameUpdated);
    }


    @Override
    public ResponseEntity<String> deleteGame(String id) {

        String gameUpdated = this.gameService.deleteGame(Long.parseLong(id));
        return ResponseEntity.ok(gameUpdated);
    }


}
