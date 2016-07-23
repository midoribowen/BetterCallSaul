package com.mpbowen.bettercallsaul.exception.exceptions;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;


public abstract class YelpAPIError extends IOException {

    private int code;
    @SerializedName("error")
    private Error error;

    public YelpAPIError(int code, Error error) {
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public Error getError() {
        return error;
    }

}
