package com.fang.ychat.pojo;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public User(String username,String password,String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
    public User(String username,String password) {
        this.username = username;
        this.password = password;

    }
    public User(Integer id,String username,String password,String nickname,String createTime) {
       this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.createTime = createTime;
    }
    public User(String username){
        this.username = username;
    }
}