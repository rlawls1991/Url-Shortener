package com.urlshortener.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseMessage {

    // 발생이유
    private String message;
    // 에러 발생이유
    private String errorMessage;
}
