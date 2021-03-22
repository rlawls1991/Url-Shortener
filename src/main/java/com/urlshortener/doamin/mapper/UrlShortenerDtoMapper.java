package com.urlshortener.doamin.mapper;

import com.urlshortener.doamin.UrlShortener;
import com.urlshortener.doamin.dto.UrlShortenerDto;

public class UrlShortenerDtoMapper {

    private static final String DEFAULT_URL = "http://localhost:8080";

    public static UrlShortenerDto mapperDto(UrlShortener urlShortener) {
       return UrlShortenerDto.createUrlShortenerDto(urlShortener, attach(urlShortener.getShortKey()));
    }

    public static String attach(String shortKey){
        return DEFAULT_URL + "/" + shortKey;
    }
}
