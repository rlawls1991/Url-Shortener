package com.urlshortener.doamin.repository;

import com.urlshortener.doamin.dto.UrlShortenerDto;

public interface UrlShortenerRepositoryCustom {

    UrlShortenerDto findByUrl(String url);
}
