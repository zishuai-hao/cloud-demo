package com.example.common.exception;


public class BaseException extends RuntimeException{
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer code) {
        // TODO 枚举
        this("系统错误错误");
    }
}
