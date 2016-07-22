package com.mpbowen.bettercallsaul.exception.exceptions;


public class UnavailableForLocation extends YelpAPIError {

    public UnavailableForLocation(int code, Error error) {
        super(code, error);
    }
}
