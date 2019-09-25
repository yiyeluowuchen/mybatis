package com.mybatis.mybatis.entity;

public class News {

    private Integer id;

    private String title;

    private String content;

    private Integer user_id;

    private User uUser;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public User getuUser() {
        return uUser;
    }

    public void setuUser(User uUser) {
        this.uUser = uUser;
    }
}
