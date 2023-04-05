package com.ecom_fin.cart.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(CartException.class)
    public ResponseEntity<Map<String,Object>> productExceptionHandler(CartException pe,WebRequest wr){
        Map map = new HashMap<>();
        map.put("message",pe.getMessage());
        map.put("details",wr.getDescription(false));
        map.put("timeStamp",LocalDateTime.now());
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    } 
}
