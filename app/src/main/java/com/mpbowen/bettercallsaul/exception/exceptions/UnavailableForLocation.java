package com.mpbowen.bettercallsaul.exception.exceptions;


public class UnavailableForLocation extends YelpAPIError {

    public UnavailableForLocation(int code, String text, String id) {
        super(code, text, id);
    }
}
