package cn.qhung.musicplayer.net.entities;

/**
 * Created by qhung on 2017/3/25.
 */

public class Response<T> {
    public static final int OK = 0;
    int code;
    String message;
    T Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
