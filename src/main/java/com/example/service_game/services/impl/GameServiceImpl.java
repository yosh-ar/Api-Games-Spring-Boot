package com.example.service_game.services.impl;
import com.example.service_game.controller.commons.annotations.CheckGameExists;

import com.example.service_game.controller.commons.entities.Game;
import com.example.service_game.controller.commons.exceptions.GameException;
import com.example.service_game.repositories.GameRepository;
import com.example.service_game.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game saveGame ( Game gameRequest) {
        Game gameCreated = gameRepository.save(gameRequest);
        return gameCreated;
    }
   /* public Game updateGame (Long id, Game gameRequest) {
        gameRequest.setId(id);
        Game gameUpdated = gameRepository.save(gameRequest);
        return gameUpdated;
    }*/
    @Override
    public Game getGame (String id) {
        Game game= gameRepository.findById(Long.valueOf(id)).orElseThrow(()-> new GameException(HttpStatus.NOT_FOUND, "Game not Found"));
        return game;
    }
    @Override
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    @CheckGameExists
    public String deleteGame(Long id) {
        gameRepository.deleteById(id);
        return "Game deleted";
    }

    @Override
    @CheckGameExists
    public Game updateGame(Game gameRequest) {
        Game updatedGame = gameRepository.save(gameRequest);
        return updatedGame;
    }

}
