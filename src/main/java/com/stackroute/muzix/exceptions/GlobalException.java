package com.stackroute.muzix.exceptions;

import com.stackroute.muzix.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<ErrorResponse> globalErrorNotfound(TrackNotFoundException ex)throws Exception
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setId(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> globalErrorAlreadyExist(TrackAlreadyExistsException ex)throws Exception
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setId(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
