package com.mybatis.mybatis.entity;

import java.io.Serializable;
//实体类都最好序列化， 有时其他类作用于该类的时候，要求序列化
public class User implements Serializable {

    private Integer id;

    private String userName;

    private String password;

    private String  realName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }



    public String toString(){

        return  "User{" +
                "id="+id+
                ",userName1='"+userName+'\''+
                ",password2='"+password+'\''+
                ",realName3='"+realName+'\''+
                '}';
        }

}
