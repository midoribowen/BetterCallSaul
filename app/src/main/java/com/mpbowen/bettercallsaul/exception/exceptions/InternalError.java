package com.mpbowen.bettercallsaul.exception.exceptions;

public class InternalError extends YelpAPIError {

    public InternalError(int code, Error error) {
        super(code, error);
    }
}
