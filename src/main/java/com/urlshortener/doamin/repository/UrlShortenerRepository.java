package com.urlshortener.doamin.repository;

import com.urlshortener.doamin.UrlShortener;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShortenerRepository extends JpaRepository<UrlShortener, Long>, UrlShortenerRepositoryCustom {
}
