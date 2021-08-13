package com.springboot.cache.utils;

public class ResultModel {

    private int code;
    private String msg;
    private Object object;

    public ResultModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultModel(int code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

    public static ResultModel ok() {
        return new ResultModel(200, "success");
    }

    public static ResultModel ok(Object object) {
        return new ResultModel(200, "success", object);
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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}    
}
