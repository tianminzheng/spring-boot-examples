package com.springmicroservice.order.client;

import java.io.Serializable;

public class UserMapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String id;
    private String userCode;
    private String userName;

    public UserMapper() {
    	
    }
    
    public UserMapper(String id, String userCode, String userName) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
