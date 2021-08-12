package com.aptalk.excetption.handler;

import com.aptalk.dto.ExceptionResponse;
import com.aptalk.excetption.NotAllowedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ExceptionResponse handleException(Exception ex) {
        return new ExceptionResponse("Exception", "알 수 없는 에러");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ExceptionResponse handleNoSuchElementException(NoSuchElementException ex) {
        return new ExceptionResponse(NoSuchElementException.class.getName(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ExceptionResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ExceptionResponse(DataIntegrityViolationException.class.getName(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ExceptionResponse handleNotAllowedException(NotAllowedException ex) {
        return new ExceptionResponse(NotAllowedException.class.getName(), ex.getMessage());
    }
}
