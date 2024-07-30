package com.example.service_game.services;

import com.example.service_game.controller.commons.entities.Game;

import java.util.Optional;

public interface GameService {
    Game saveGame (Game gameRequest);
    Game getGame (String id);
    Game updateGame (Game gameRequest);
    Optional<Game> getGameById(Long id);
    String deleteGame (Long id);

}
