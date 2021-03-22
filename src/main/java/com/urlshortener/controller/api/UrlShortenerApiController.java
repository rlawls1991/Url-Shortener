package com.urlshortener.controller.api;


import com.urlshortener.controller.dto.UrlShortenerParamDto;
import com.urlshortener.controller.validation.UrlValidation;
import com.urlshortener.doamin.service.UrlShortenerService;
import com.urlshortener.error.ApiResponseMessage;
import com.urlshortener.error.ErrorResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/url", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UrlShortenerApiController {

    private final UrlShortenerService urlShortenerService;

    @PostMapping
    public ResponseEntity createUrlShortener(@RequestBody @Valid UrlShortenerParamDto urlShortenerDto, Errors errors) {
        if(errors.hasErrors()){
            return badRequest(errors);
        }
        if(UrlValidation.checkUrl(urlShortenerDto.getUrl())){
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage("URL을 확인해주세요", "URL형식이 맞지 않습니다.");
            return new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(urlShortenerService.createUrlShortener(urlShortenerDto), HttpStatus.CREATED);
    }

    private ResponseEntity<?> badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorResponseMessage(errors));
    }
}
