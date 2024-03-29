package com.fnhelper.fnapp.data;

public class BaseResult<T> {

    private String code;
    private String msg;
    private T data;

    public BaseResult() {
    }

    public BaseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResult{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }
}
