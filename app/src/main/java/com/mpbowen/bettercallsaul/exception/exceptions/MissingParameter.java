package com.mpbowen.bettercallsaul.exception.exceptions;


public class MissingParameter extends YelpAPIError {

    public MissingParameter(int code, String text, String id) {
        super(code, text, id);
    }
}
