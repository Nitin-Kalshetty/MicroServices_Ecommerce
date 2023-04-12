package com.rockerBank.details.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHanlder {
    

    @ExceptionHandler(AccountHolderException.class)
    public ResponseEntity<Map<String,Object>> AccountHolderExceptionHandler(AccountHolderException pe,WebRequest wr){
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
