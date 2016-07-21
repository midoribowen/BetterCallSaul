package com.mpbowen.bettercallsaul.exception.exceptions;

public class InternalError extends YelpAPIError {

    public InternalError(int code, String text, String id) {
        super(code, text, id);
    }
}
