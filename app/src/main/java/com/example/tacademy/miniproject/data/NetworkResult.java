package com.example.tacademy.miniproject.data;

/**
 * Created by Tacademy on 2016-08-10.
 */
public class NetworkResult<T> {
    private T result;
    private int code;

    public T getResult(){
        return this.result;
    }

    public int getCode() {
        return this.code;
    }
}
