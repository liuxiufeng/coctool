package com.shadow.coctool.retrofit;

/**
 * Created by lxf on 2017/7/4.
 */

public class Result<T> {

    private boolean success;

    private String message;

    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
