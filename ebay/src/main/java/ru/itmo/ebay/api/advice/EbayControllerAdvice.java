package ru.itmo.ebay.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class EbayControllerAdvice {

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> onResourceAccessException(ResourceAccessException e){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Remote service isn't available now!");
    }
}
