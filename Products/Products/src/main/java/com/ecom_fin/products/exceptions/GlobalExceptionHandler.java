package com.ecom_fin.products.exceptions;

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
    
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Map<String,Object>> productExceptionHandler(ProductException pe,WebRequest wr){
        Map map = new HashMap<>();
        map.put("message",pe.getMessage());
        map.put("details",wr.getDescription(false));
        map.put("timeStamp",LocalDateTime.now());
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    } 

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> ExceptionHandler(Exception pe,WebRequest wr){
        Map map = new HashMap<>();
        map.put("message",pe.getMessage());
        map.put("details",wr.getDescription(false));
        map.put("timeStamp",LocalDateTime.now());
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
}
