package com.bluemsun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Blog {

    private int id;
    private int userId;//用户id
    private String username;//用户名
    private String title;//标题
    private Timestamp createTime;//创建时间(最后一次修改时间)
    private int views;//浏览量
    private String content;//文章
    private int likesNum;//点赞数

    public Blog() {
    }

    public Blog(int id, int userId, String username, String title, Timestamp createTime, int views, String content, int likesNum) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.createTime = createTime;
        this.views = views;
        this.content = content;
        this.likesNum = likesNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(int likesNum) {
        this.likesNum = likesNum;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", views=" + views +
                ", content='" + content + '\'' +
                ", likesNum=" + likesNum +
                '}';
    }
}
