package com.urlshortener.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urlshortener.common.RestDocsConfiguration;
import com.urlshortener.controller.dto.UrlShortenerParamDto;
import com.urlshortener.doamin.UrlShortener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional(readOnly = true)
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
public class UrlShortenerApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TEST_URL = "https://github.com/rlawls1991/Url-Shortener";

    @Test
    @Transactional
    @DisplayName("정상적인 ShortURL 생성")
    void createUrlShortener() throws Exception {
        // Given
        UrlShortenerParamDto paramDto = UrlShortenerParamDto.mockMvcUrlShortenerDto()
                .url(TEST_URL)
                .build();

        // When
        ResultActions perform = mockMvc.perform(post("/api/url")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(paramDto)))
                .andDo(print());

        // Then
        perform.andExpect(status().isCreated())
                .andExpect(jsonPath("originalUrl").exists())
                .andExpect(jsonPath("shortUrl").exists())
                .andExpect(jsonPath("shortKey").exists())
                .andExpect(jsonPath("searchCount").exists())
                .andDo(document("shortURL 생성"))
                .andDo(print())
        ;
    }

    @Test
    @Transactional
    @DisplayName("입력 받을 수 없는 값을 사용한 경우에 에러가 발생하는 테스트")
    void createUrlShortener_Bad_Request() throws Exception {
        // Given
        UrlShortener urlShortener = UrlShortener.mockMvcUrlShortener()
                .url(TEST_URL)
                .build();

        // When
        ResultActions perform = mockMvc.perform(post("/api/url")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(urlShortener)))
                .andDo(print());

        // Then
        perform.andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    @DisplayName("URL의 형식이 맞지 않는경우 에러가 발생하는 테스트")
    void createShortener_Bad_Request_Wrong_Input() throws Exception {
        // Given
        UrlShortenerParamDto paramDto = UrlShortenerParamDto.mockMvcUrlShortenerDto()
                .url("12323232")
                .build();

        // When
        ResultActions perform = mockMvc.perform(post("/api/url")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(paramDto)))
                .andDo(print());

        // Then
        perform.andExpect(status().isBadRequest());
    }
}