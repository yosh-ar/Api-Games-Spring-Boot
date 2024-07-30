package com.example.service_game.controller.impl;

import com.example.service_game.controller.commons.dto.ErrorResponse;
import com.example.service_game.controller.commons.exceptions.GameException;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExecptionHandlerController {
    @ExceptionHandler({MethodArgumentNotValidException.class, GameException.class})
    public ResponseEntity<Object> handleExceptions(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException validationException = (MethodArgumentNotValidException) ex;
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : validationException.getBindingResult().getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else if (ex instanceof GameException) {
            GameException gameException = (GameException) ex;
            log.error("New exception", gameException);
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(gameException.getHttpStatus().value())
                    .message(gameException.getMessage())
                    .build();
            return new ResponseEntity<>(errorResponse, gameException.getHttpStatus());
        } else {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("An unexpected error occurred")
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
