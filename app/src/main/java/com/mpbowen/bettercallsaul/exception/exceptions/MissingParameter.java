package com.mpbowen.bettercallsaul.exception.exceptions;


public class MissingParameter extends YelpAPIError {

    public MissingParameter(int code, Error error) {
        super(code, error);
    }
}
