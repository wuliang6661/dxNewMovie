package com.myp.cinema.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/11.
 */

public class BaseBO implements Serializable {
    private Object code;
    private Object data;
    private String message;
    private int status;

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}





