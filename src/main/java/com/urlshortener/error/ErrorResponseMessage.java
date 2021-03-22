package com.urlshortener.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseMessage {
    private Errors errors;
}
