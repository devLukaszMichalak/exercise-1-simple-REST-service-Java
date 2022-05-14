package com.lukas.hiweb.user.exceptions;

public class DivideByZeroException extends RuntimeException {
    public DivideByZeroException() {
        super("Calculations cannot be performed - follower number is 0!");
    }
}
