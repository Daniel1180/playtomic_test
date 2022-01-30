package com.playtomic.tests.wallet.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.playtomic.tests.wallet.exceptions.CustomException;

@RestControllerAdvice
public class WalletExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value= { CustomException.class })
    protected ResponseEntity<Object> handleWalletException(CustomException ex, WebRequest request) {
        logger.error(ex.toString());
        HttpStatus status = HttpStatus.valueOf(ex.getErrorCode());
        return ResponseEntity.status(status).body(ex.getMessage());
    }

}
