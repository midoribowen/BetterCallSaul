package com.mpbowen.bettercallsaul.exception.exceptions;


public class ExceededReqs extends YelpAPIError {

    public ExceededReqs(int code, String text, String id) {
        super(code, text, id);
    }
}
