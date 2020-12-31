package com.tommy.board.global.exception;

public class PostNotFoundException extends IllegalStateException {

    public PostNotFoundException() {
        super(ExceptionMessage.MESSAGE_NOT_FOUND_POST);
    }

}
