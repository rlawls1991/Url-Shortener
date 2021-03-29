package com.urlshortener.controller;


import com.urlshortener.doamin.dto.UrlShortenerDto;
import com.urlshortener.doamin.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    /**
     * URL 입력 페이지 및 ShortUrl 제공
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/{shortKey:[0-9a-zA-Z]+}")
    private String redirectTo(@PathVariable("shortKey") String shortKey) {
        UrlShortenerDto urlShortenerDto = urlShortenerService.findByShortKey(shortKey.trim());
        return "redirect:" + urlShortenerDto.getOriginalUrl();
    }
}
