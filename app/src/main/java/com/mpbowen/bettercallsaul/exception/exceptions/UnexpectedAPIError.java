package com.mpbowen.bettercallsaul.exception.exceptions;


public class UnexpectedAPIError extends YelpAPIError {
    public UnexpectedAPIError(int code) {
        this(code, null);
    }

    public UnexpectedAPIError(int code, Error error) {
        super(code, error);
    }
}
