package com.example.service_game.controller.commons.aspects;
import com.example.service_game.controller.commons.entities.Game;
import com.example.service_game.controller.commons.exceptions.GameException;
import com.example.service_game.repositories.GameRepository;
import com.example.service_game.services.GameService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckGameExistsAspect {

    private final GameService gameService;

    public CheckGameExistsAspect(GameService gameService) {
        this.gameService = gameService;
    }

    @Around("@annotation(com.example.service_game.controller.commons.annotations.CheckGameExists)")
    public Object checkGameExistence(ProceedingJoinPoint joinPoint) throws Throwable {
        Long id = null;
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Long) {
                id = (Long) arg;
                break;
            } else if (arg instanceof Game) {
                id = ((Game) arg).getId();
                break;
            }
        }
        if (id != null && !gameService.getGameById(id).isPresent()) {
            throw new GameException(HttpStatus.NOT_FOUND, "Game not Found");
        }
        return joinPoint.proceed();
    }
}