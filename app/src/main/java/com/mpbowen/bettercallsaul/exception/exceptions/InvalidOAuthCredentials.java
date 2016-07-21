package com.mpbowen.bettercallsaul.exception.exceptions;


public class InvalidOAuthCredentials extends YelpAPIError {

    public InvalidOAuthCredentials(int code, String text, String id) {
        super(code, text, id);
    }
}
