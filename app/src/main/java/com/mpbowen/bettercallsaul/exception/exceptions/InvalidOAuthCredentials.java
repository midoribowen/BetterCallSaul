package com.mpbowen.bettercallsaul.exception.exceptions;


public class InvalidOAuthCredentials extends YelpAPIError {

    public InvalidOAuthCredentials(int code, Error error) {
        super(code, error);
    }
}
