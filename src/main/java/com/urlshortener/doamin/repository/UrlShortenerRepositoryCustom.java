package com.urlshortener.doamin.repository;

import com.urlshortener.doamin.dto.UrlShortenerDto;

public interface UrlShortenerRepositoryCustom {

    /**
     * originalUrl를 기준으로 UrlShort 찾기
     * @param originalUrl
     * @return
     */
    UrlShortenerDto findByUrl(String originalUrl);
}
