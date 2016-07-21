package com.mpbowen.bettercallsaul.exception.exceptions;


public class InvalidOAuthUser extends YelpAPIError {

    public InvalidOAuthUser(int code, String text, String id) {
        super(code, text, id);
    }
}
