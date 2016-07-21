package com.mpbowen.bettercallsaul.exception.exceptions;


public class UnexpectedAPIError extends YelpAPIError {
    public UnexpectedAPIError(int code) {
        this(code, null, null);
    }

    public UnexpectedAPIError(int code, String text, String id) {
        super(code, text, id);
    }
}
