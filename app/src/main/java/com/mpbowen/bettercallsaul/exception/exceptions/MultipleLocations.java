package com.mpbowen.bettercallsaul.exception.exceptions;


public class MultipleLocations extends YelpAPIError {

    public MultipleLocations(int code, String text, String id) {
        super(code, text, id);
    }
}
