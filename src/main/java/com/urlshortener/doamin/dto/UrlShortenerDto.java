package com.urlshortener.doamin.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.urlshortener.doamin.UrlShortener;
import lombok.Getter;

@Getter
public class UrlShortenerDto {
    private String originalUrl; // 처음받은 URL
    private String shortUrl; // 줄인 URL
    private String shortKey; // shortKey
    private Long searchCount;

    @QueryProjection
    public UrlShortenerDto(String originalUrl, String shortKey, Long searchCount) {
        this.originalUrl = originalUrl;
        this.shortKey = shortKey;
        this.searchCount = searchCount;
    }

    public UrlShortenerDto(String originalUrl, String shortUrl, String shortKey, Long searchCount) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.shortKey = shortKey;
        this.searchCount = searchCount;
    }

    public void setShortUrl(String shortUrl){
        this.shortUrl = shortUrl;
    }

    public static UrlShortenerDto createUrlShortenerDto(UrlShortener urlShortener, String shortUrl) {
        return new UrlShortenerDto(urlShortener.getUrl(), shortUrl, urlShortener.getShortKey(), urlShortener.getSearchCount());
    }
}
