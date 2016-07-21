package com.mpbowen.bettercallsaul.exception.exceptions;


public class InvalidSignature extends YelpAPIError {

    public InvalidSignature(int code, String text, String id) {
        super(code, text, id);
    }
}
