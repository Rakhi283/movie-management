package com.Zymr.assessment.ExceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Zymr.assessment.dto.ApiError;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.Zymr.assessment.controller")
public class RestResponseEntityExceptionHandler {
	
	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ApiError> handleMovieNotFound(MovieNotFoundException ex) {
       ApiError error = new ApiError(); 
       error.setMessage(ex.getMessage());
       error.setStatusCode(HttpStatus.NOT_FOUND.value());
       error.setStatus(HttpStatus.NOT_FOUND.toString());
       error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
    	ApiError error = new ApiError(); 
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
