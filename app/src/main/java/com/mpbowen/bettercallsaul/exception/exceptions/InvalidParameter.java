package com.mpbowen.bettercallsaul.exception.exceptions;


public class InvalidParameter extends YelpAPIError {

    public InvalidParameter(int code, Error error) {
        super(code, error);
    }
}
