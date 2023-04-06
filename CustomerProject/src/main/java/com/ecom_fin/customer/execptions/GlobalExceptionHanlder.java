package com.ecom_fin.customer.execptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHanlder {
    

    @ExceptionHandler(UserException.class)
    public ResponseEntity<CustomError> userExceptionHandler(UserException ue,WebRequest wr){
        CustomError build = CustomError.builder().message(ue.getMessage()).timestamp(LocalDateTime.now()).details(wr.getDescription(false)).build();
        return new ResponseEntity<CustomError>(build, HttpStatus.BAD_REQUEST);
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
