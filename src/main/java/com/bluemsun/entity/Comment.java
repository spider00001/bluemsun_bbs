package com.bluemsun.entity;

import java.sql.Timestamp;

public class Comment {
    private int id;
    private String content;//评论内容
    private int blogId;//博客id
    private Timestamp createTime;//评论创建时间
    private int userId;//评论的用户id
    private int form;//形式(0：博客的评论；1：评论的评论)
    private int likesNum;//点赞数

    public Comment() {
    }

    public Comment(int id, String content, int blogId, Timestamp createTime, int userId, int form, int likesNum) {
        this.id = id;
        this.content = content;
        this.blogId = blogId;
        this.createTime = createTime;
        this.userId = userId;
        this.form = form;
        this.likesNum = likesNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(int likesNum) {
        this.likesNum = likesNum;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", blogId=" + blogId +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", form=" + form +
                ", likesNum=" + likesNum +
                '}';
    }
}
