package com.mpbowen.bettercallsaul.exception.exceptions;


public class BusinessUnavailable extends YelpAPIError {

    public BusinessUnavailable(int code, Error error) {
        super(code, error);
    }
}
