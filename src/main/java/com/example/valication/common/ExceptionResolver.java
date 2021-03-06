package com.example.valication.common;

import com.example.valication.exception.ParamException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice("com.example.valication")
public class ExceptionResolver extends ResponseEntityExceptionHandler {


   /* @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        if (e instanceof ParamException){
            JsonData result = JsonData.fail(e.getMessage());
            mv = new ModelAndView("jsonView",result.toMap());
        }

        return mv;
    }*/
   @Override
   protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

       Map<String, String> messages = new HashMap<>();
       BindingResult result = ex.getBindingResult();
       if (result.hasErrors()) {
           List<ObjectError> errors = result.getAllErrors();
           for (ObjectError error : errors) {
               FieldError fieldError = (FieldError) error;
               messages.put(fieldError.getField(), fieldError.getDefaultMessage());
           }
           //logger.error(messages.toString());
       }
       JsonData jsonData = JsonData.fail(messages);
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonData);
   }

}
