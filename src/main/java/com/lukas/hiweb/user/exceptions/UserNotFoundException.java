package com.lukas.hiweb.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String login) {
        super("User " + login + " not found!");
    }
}