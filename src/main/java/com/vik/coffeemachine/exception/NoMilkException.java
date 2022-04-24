package com.vik.coffeemachine.exception;

public class NoMilkException extends RuntimeException {
    public NoMilkException() {
        super("Add milk to machine!");
    }
}