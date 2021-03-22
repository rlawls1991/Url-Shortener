package com.urlshortener.doamin.service;

import com.urlshortener.controller.dto.UrlShortenerParamDto;
import com.urlshortener.doamin.UrlShortener;
import com.urlshortener.doamin.dto.UrlShortenerDto;
import com.urlshortener.doamin.mapper.UrlShortenerDtoMapper;
import com.urlshortener.doamin.module.UrlEncoder;
import com.urlshortener.doamin.repository.UrlShortenerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final UrlShortenerRepository urlShortenerRepository;

    @Override
    @Transactional
    public UrlShortenerDto createUrlShortener(UrlShortenerParamDto urlShortenerParamDto) {
        String url = urlShortenerParamDto.getUrl();
        UrlShortenerDto urlShortenerDto = urlShortenerRepository.findByUrl(url);

        if (urlShortenerDto == null) {
            String shortKey = UrlEncoder.encoding(Long.valueOf(url.length()));
            UrlShortener createUrlShortener = UrlShortener.createUrlShortener(url, shortKey);
            urlShortenerRepository.save(createUrlShortener);

            return UrlShortenerDtoMapper.mapperDto(createUrlShortener);
        }

        return urlShortenerDto;
    }
}
