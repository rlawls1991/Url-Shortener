package com.urlshortener.controller.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@Getter
@NoArgsConstructor
public class UrlShortenerParamDto {
    @NotEmpty
    private String url;

    @Builder(builderMethodName = "mockMvcUrlShortenerDto")
    private UrlShortenerParamDto(String url) {
        this.url = url;
    }
}