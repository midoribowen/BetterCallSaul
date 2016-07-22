package com.mpbowen.bettercallsaul.exception.exceptions;


public class InvalidSignature extends YelpAPIError {

    public InvalidSignature(int code, Error error) {
        super(code, error);
    }
}
