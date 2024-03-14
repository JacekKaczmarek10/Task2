package com.energysolution.iot.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ValidationExceptionHandlerTest {

    @Nested
    class HandleValidationExceptionsTest {

        @Test
        void shouldReturnBadRequest() {
            final var exception = Mockito.mock(MethodArgumentNotValidException.class);
            final var bindingResult = Mockito.mock(BindingResult.class);
            final var fieldError = new FieldError("objectName", "fieldName", "error message");
            final var handler = new ValidationExceptionHandler();
            when(exception.getBindingResult()).thenReturn(bindingResult);
            when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));

            final var response = handler.handleValidationExceptions(exception);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        void shouldReturnExpectedErrors() {
            final var exception = Mockito.mock(MethodArgumentNotValidException.class);
            final var bindingResult = Mockito.mock(BindingResult.class);
            final var fieldError = new FieldError("objectName", "fieldName", "error message");
            final var handler = new ValidationExceptionHandler();
            when(exception.getBindingResult()).thenReturn(bindingResult);
            when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));

            final var response = handler.handleValidationExceptions(exception);

            final var expectedErrors = new HashMap<>();
            expectedErrors.put("fieldName", "error message");
            assertEquals(expectedErrors, response.getBody());
        }
    }

}