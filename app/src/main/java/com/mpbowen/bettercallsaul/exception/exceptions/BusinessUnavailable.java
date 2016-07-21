package com.mpbowen.bettercallsaul.exception.exceptions;


public class BusinessUnavailable extends YelpAPIError {

    public BusinessUnavailable(int code, String text, String id) {
        super(code, text, id);
    }
}
