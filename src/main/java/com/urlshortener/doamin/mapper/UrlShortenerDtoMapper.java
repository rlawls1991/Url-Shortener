package com.urlshortener.doamin.mapper;

import com.urlshortener.doamin.UrlShortener;
import com.urlshortener.doamin.dto.UrlShortenerDto;

public class UrlShortenerDtoMapper {

    private static final String DEFAULT_URL = "http://localhost:8080";

    public static UrlShortenerDto mapperDto(UrlShortener urlShortener) {
        UrlShortenerDto urlShortenerDto = UrlShortenerDto.createUrlShortenerDto(urlShortener, attach(urlShortener.getShortKey()));

        return urlShortenerDto;
    }

    public static String attach(String shortKey) {
        return DEFAULT_URL + "/" + shortKey;
    }
}
