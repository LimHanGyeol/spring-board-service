package com.tommy.board.global.exception.error;

import com.tommy.board.global.exception.ExceptionMessage;
import lombok.Getter;

@Getter
public enum ErrorCode {

    // Post
    POST_NOT_FOUND(404,"P001", ExceptionMessage.MESSAGE_NOT_FOUND_POST);

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
