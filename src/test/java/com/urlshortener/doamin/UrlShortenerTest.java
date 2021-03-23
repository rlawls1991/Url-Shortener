package com.urlshortener.doamin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UrlShortenerTest {

    private static final String TEST_URL = "https://github.com/rlawls1991/Url-Shortener";


    @Test
    @DisplayName("UrlShortener에서 조회가 증가하였는가")
    public void addUrlShortener() {
        UrlShortener urlShortener = UrlShortener.mockMvcUrlShortener()
                .url(TEST_URL)
                .shortKey("123")
                .build();

        urlShortener.addSearchCount();

        assertEquals(urlShortener.getSearchCount(), 1);
    }

}