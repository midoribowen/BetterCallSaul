package com.mpbowen.bettercallsaul.exception.exceptions;


public class InvalidParameter extends YelpAPIError {

    public InvalidParameter(int code, String text, String id) {
        super(code, text, id);
    }
}
