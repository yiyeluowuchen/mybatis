package com.mybatis.mybatis.entity;

import lombok.Data;
import java.io.Serializable;

@Data//这个注解是lombok的，这样可以不用写getter/setter方法
public class Auth implements Serializable {



    private int id;

    private String username;

    private String password;

    /**
     * 角色应该
     */

    private String auth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuth() {
        return auth;
    }

    public void setAut(String auth) {
        this.auth = auth;
    }






}
