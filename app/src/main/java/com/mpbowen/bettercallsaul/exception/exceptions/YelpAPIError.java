package com.mpbowen.bettercallsaul.exception.exceptions;

import java.io.IOException;


public abstract class YelpAPIError extends IOException {

    private int code;
    private String text;
    private String id;

    public YelpAPIError(int code, String text, String id) {
        this.code = code;
        this.text = text;
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

}
