package com.example.demo.exception;

public class DemoException extends RuntimeException {
    private int code;

    private String msg;

    public DemoException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DemoException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
