package com.vik.coffeemachine.exception;

public class NoCoffeeException extends RuntimeException {
    public NoCoffeeException() {
        super("Add coffee to machine!");
    }
}