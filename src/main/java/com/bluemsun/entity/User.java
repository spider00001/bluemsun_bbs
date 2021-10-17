package com.bluemsun.entity;

public class User {

    private int id;//id
    private String username;//用户名
    private String password;//密码
    private int sex;//性别
    private String headPortrait;//头像资源路径
    private int status;//账号状态
    private String description;//简介
    private String stuNumber;//学号
    private int fansNum;//粉丝数量
    private int followUserNum;//关注数量
    private String phoneNumber;//手机号
    private String emailCount;//邮箱

    public User() {
    }

    public User(int id, String username, String password, int sex, String headPortrait, int status, String description, String stuNumber, int fansNum, int followUserNum, String phoneNumber, String emailCount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.headPortrait = headPortrait;
        this.status = status;
        this.description = description;
        this.stuNumber = stuNumber;
        this.fansNum = fansNum;
        this.followUserNum = followUserNum;
        this.phoneNumber = phoneNumber;
        this.emailCount = emailCount;
    }

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getFollowUserNum() {
        return followUserNum;
    }

    public void setFollowUserNum(int followUserNum) {
        this.followUserNum = followUserNum;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailCount() {
        return emailCount;
    }

    public void setEmailCount(String emailCount) {
        this.emailCount = emailCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", headPortrait='" + headPortrait + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", fansNum=" + fansNum +
                ", followUserNum=" + followUserNum +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailCount='" + emailCount + '\'' +
                '}';
    }
}
