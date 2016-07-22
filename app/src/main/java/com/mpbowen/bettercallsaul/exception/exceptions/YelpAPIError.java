package com.mpbowen.bettercallsaul.exception.exceptions;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;


public abstract class YelpAPIError extends IOException {

    private int code;
    @SerializedName("error")
    private Error error;

//    public YelpAPIError() {
//    }

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

//    private int code;
//    private String message;
//    private String text;
//    private String errorId;
//
//    public YelpAPIError(int code, String message, String text, String errorId) {
//        this.code = code;
//        this.message = message;
//        this.text = text;
//        this.errorId = errorId;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public String getErrorId() {
//        return errorId;
//    }

}
