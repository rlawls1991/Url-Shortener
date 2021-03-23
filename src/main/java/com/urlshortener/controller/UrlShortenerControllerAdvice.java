package com.urlshortener.controller;

import com.urlshortener.controller.exception.ShortUrlNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class UrlShortenerControllerAdvice {

    /**
     * ShortUrlNotFoundException 발생시 shortUrlNotFoundPage 로 이동
     * @param e
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ShortUrlNotFoundException.class)
    public String ShortUrlNotFoundHandler(ShortUrlNotFoundException e, HttpServletRequest request) {
        log.info("URL을 잘못 입력하셨습니다.");
        log.info(String.valueOf(request.getRequestURL()));
        return "error/shortUrlNotFoundPage";
    }
}
