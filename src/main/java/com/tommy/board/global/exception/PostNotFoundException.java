package com.tommy.board.global.exception;

import com.tommy.board.global.exception.error.BusinessException;
import com.tommy.board.global.exception.error.ErrorCode;

public class PostNotFoundException extends BusinessException {

    public PostNotFoundException() {
        super(ExceptionMessage.MESSAGE_NOT_FOUND_POST, ErrorCode.POST_NOT_FOUND);
    }

}
