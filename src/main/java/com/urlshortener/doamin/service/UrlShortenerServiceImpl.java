package com.urlshortener.doamin.service;

import com.urlshortener.controller.dto.UrlShortenerParamDto;
import com.urlshortener.controller.exception.ShortUrlNotFoundException;
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
        String url = urlShortenerParamDto.getUrl().trim();
        UrlShortenerDto urlShortenerDto = urlShortenerRepository.findByUrl(url);

        // 해당 URL이 존재하지 않는다면 생성
        if (urlShortenerDto == null) {
            UrlShortener createUrlShortener = UrlShortener.createUrlShortener(url);
            urlShortenerRepository.save(createUrlShortener);
            String shortKey = UrlEncoder.encoding(createUrlShortener.getId());
            createUrlShortener.addShortKey(shortKey);

            return UrlShortenerDtoMapper.mapperDto(createUrlShortener);
        }

        return UrlShortenerDtoMapper.mapperDto(urlShortenerDto);
    }

    @Override
    @Transactional
    public UrlShortenerDto findByShortKey(String shortKey) {
        UrlShortener urlShortener = urlShortenerRepository.findByShortKey(shortKey);

        if (urlShortener == null) {
            throw new ShortUrlNotFoundException("해당 URL은 존재하지 않습니다.");
        }

        // 조회횟수 1증가
        urlShortener.addSearchCount();

        return UrlShortenerDtoMapper.mapperDto(urlShortener);
    }
}
