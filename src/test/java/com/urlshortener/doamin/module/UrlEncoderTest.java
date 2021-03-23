package com.urlshortener.doamin.module;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UrlEncoderTest {

    @ParameterizedTest
    @MethodSource("checkSameValueParameterized")
    @DisplayName("같은 값을 파라미터로 주게 되면 같은 값이 나오는지")
    public void checkSameValue(Long param){
        String first = UrlEncoder.encoding(param);
        String second = UrlEncoder.encoding(param);
        assertEquals(first, second);
    }

    private static Stream<Arguments> checkSameValueParameterized() { // argument source method
        return Stream.of(
                Arguments.of(1L),
                Arguments.of(2L),
                Arguments.of(3000L),
                Arguments.of(99999L)
        );
    }
}