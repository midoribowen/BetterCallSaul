package com.mpbowen.bettercallsaul.exception.exceptions;


public class InvalidOAuthUser extends YelpAPIError {

    public InvalidOAuthUser(int code, Error error) {
        super(code, error);
    }
}
