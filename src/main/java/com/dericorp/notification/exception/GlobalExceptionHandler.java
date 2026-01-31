package com.dericorp.notification.exception;


import com.dericorp.notification.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<ErrorResponse> handleNotificationException(NotificationException ex, HttpServletRequest request) {

        logger.error("EmailNotificationException Caught ......", ex);
        logger.error(ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse(LocalDateTime.now().toString(), HttpStatus.SERVICE_UNAVAILABLE.value(),
                HttpStatus.SERVICE_UNAVAILABLE.name(), ex.getMessage(), request.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);

    }
}
