package com.mpbowen.bettercallsaul.exception.exceptions;


public class ExceededReqs extends YelpAPIError {

    public ExceededReqs(int code, Error error) {
        super(code, error);
    }
}
