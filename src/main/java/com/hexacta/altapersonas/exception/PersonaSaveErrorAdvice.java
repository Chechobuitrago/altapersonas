package com.hexacta.altapersonas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PersonaSaveErrorAdvice {
    @ResponseBody
    @ExceptionHandler(PersonaSaveErrorException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Map<String,String> exceptionHandler(PersonaNotFoundException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("Mensaje de error",exception.getMessage());

        return errorMap;
    }}
