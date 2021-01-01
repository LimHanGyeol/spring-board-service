package com.tommy.board.global.exception;

import com.tommy.board.global.exception.error.BusinessException;
import com.tommy.board.global.exception.error.ErrorCode;
import com.tommy.board.global.exception.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPostException(BusinessException e) {
        log.error("PostEntity NotFoundException", e);
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.from(errorCode);
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

}
