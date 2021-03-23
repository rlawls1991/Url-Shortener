package com.urlshortener.doamin.exception;

public class ShortKeyOutLengthException extends RuntimeException {

    public ShortKeyOutLengthException(String message) {
        super(message);
    }
}
