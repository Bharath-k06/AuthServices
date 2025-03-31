package com.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    ResponseEntity<errorResponse> handleException(CustomException ex){

        errorResponse obj = new errorResponse();
        obj.setErrorCode(ex.getErrorCode());
        obj.setMessage(ex.getErrorMessage());
        return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
    }
}

class errorResponse{
    public String errorCode;
    public String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
