package com.example.service_game.controller;

import com.example.service_game.controller.commons.constants.ApiPathVariables;
import com.example.service_game.controller.commons.entities.Game;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathVariables.V1_ROUTE + ApiPathVariables.GAME_ROUTE)

public interface GameApi {
    @PostMapping
    ResponseEntity<Game> createGame(@Valid @RequestBody Game game);

    @GetMapping("/{id}")
    ResponseEntity<Game> getGame(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<Game> updateGame(@PathVariable String id, @Valid @RequestBody Game game);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteGame(@PathVariable String id);

}
