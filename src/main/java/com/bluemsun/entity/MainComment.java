package com.bluemsun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

//博客的主评论区
public class MainComment {

    private int id;
    private String content;//评论内容
    private int blogId;//博客id
    private Timestamp createTime;//评论创建时间
    private int userId;//评论的用户id
    private int likesNum;//点赞数
    private int isMy;

    public MainComment() {
    }

    public MainComment(int id, String content, int blogId, Timestamp createTime, int userId, int likesNum, int isMy) {
        this.id = id;
        this.content = content;
        this.blogId = blogId;
        this.createTime = createTime;
        this.userId = userId;
        this.likesNum = likesNum;
        this.isMy = isMy;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(int likesNum) {
        this.likesNum = likesNum;
    }

    public int getIsMy() {
        return isMy;
    }

    public void setIsMy(int isMy) {
        this.isMy = isMy;
    }

    @Override
    public String toString() {
        return "MainComment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", blogId=" + blogId +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", likesNum=" + likesNum +
                ", isMy=" + isMy +
                '}';
    }
}
