package com.DyGames.user_service.exception;

public class UsernameExisteException extends RuntimeException {
    public UsernameExisteException(String message) {
        super(message);
    }
}
