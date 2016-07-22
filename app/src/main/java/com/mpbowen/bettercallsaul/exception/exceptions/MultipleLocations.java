package com.mpbowen.bettercallsaul.exception.exceptions;


public class MultipleLocations extends YelpAPIError {

    public MultipleLocations(int code, Error error) {
        super(code, error);
    }
}
