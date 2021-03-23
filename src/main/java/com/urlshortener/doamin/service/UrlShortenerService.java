package com.urlshortener.doamin.service;

import com.urlshortener.controller.dto.UrlShortenerParamDto;
import com.urlshortener.doamin.dto.UrlShortenerDto;

public interface UrlShortenerService {

    /**
     * shortUrl 생성
     * @param urlShortenerParamDto
     * @return
     */
    UrlShortenerDto createUrlShortener(UrlShortenerParamDto urlShortenerParamDto);


    /**
     * shortKey를 이용한 original url 찾기
     * @param shortKey
     * @return
     */
    UrlShortenerDto findByShortKey(String shortKey);
}
