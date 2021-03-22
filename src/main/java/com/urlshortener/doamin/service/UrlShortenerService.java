package com.urlshortener.doamin.service;

import com.urlshortener.controller.dto.UrlShortenerParamDto;
import com.urlshortener.doamin.dto.UrlShortenerDto;

public interface UrlShortenerService {

    /**
     * UrlShort 생성
     * @param urlShortenerParamDto
     * @return
     */
    UrlShortenerDto createUrlShortener(UrlShortenerParamDto urlShortenerParamDto);
}
