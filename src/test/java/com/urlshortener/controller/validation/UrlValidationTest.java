package com.urlshortener.controller.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UrlValidationTest {

    /*
     * 정상 = false </br>
     * 비정상 = true </br>
     */

    @ParameterizedTest
    @MethodSource("trueURLParameterized")
    @DisplayName("URL 형식이 맞는지")
    void checkUrLValidation_TRUE(String url) {
        assertThat(false).isEqualTo(UrlValidation.checkUrl(url));
    }


    @ParameterizedTest
    @MethodSource("falseURLParameterized")
    @DisplayName("URL 형식이 아닌지")
    void checkUrLValidation_FALSE(String url) {
        assertThat(true).isEqualTo(UrlValidation.checkUrl(url));
    }

    private static Stream<Arguments> falseURLParameterized() { // argument source method
        return Stream.of(
                Arguments.of("123444"),
                Arguments.of("https1://rlaw"),
                Arguments.of("d@#@@#$fffffff"),
                Arguments.of("rla@test.com")
        );
    }

    private static Stream<Arguments> trueURLParameterized() { // argument source method
        return Stream.of(
                Arguments.of("https://github.com/rlawls1991/Url-Shortener"),
                Arguments.of("https://github.com/rlawls1991/Study_RestAPI"),
                Arguments.of("https://rlawls1991.tistory.com/")
        );
    }
}