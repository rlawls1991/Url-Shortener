package com.urlshortener.controller.exception;

public class ShortUrlNotFoundException extends RuntimeException {

    public ShortUrlNotFoundException(String message) {
        super("URL을 잘못 입력하셨습니다.");
    }
}
