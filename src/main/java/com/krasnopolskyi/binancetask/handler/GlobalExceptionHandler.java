package com.krasnopolskyi.binancetask.handler;

import com.krasnopolskyi.binancetask.exception.CryptoPairNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
        log.error("Unknown error occurred", exception);
        return ResponseEntity.internalServerError().body("Sorry but something went wrong");
    }

    @ExceptionHandler(CryptoPairNameException.class)
    public ResponseEntity<Object> handleCryptoPairNameException(
            CryptoPairNameException exception, WebRequest request) {
        log.warn("Problem with crypto name ", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CryptoPair not found");
    }
}
