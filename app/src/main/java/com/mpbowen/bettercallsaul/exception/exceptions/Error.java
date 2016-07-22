package com.mpbowen.bettercallsaul.exception.exceptions;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;

public class Error {

    @SerializedName("text")
    private String text;

    @SerializedName("id")
    private String id;

    @SerializedName("field")
    private String field;

//    public Error() {
//    }

    public Error(String text, String id, String field) {
        this.text = text;
        this.id = id;
        this.field = field;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public String getField() {
        return field;
    }

}
