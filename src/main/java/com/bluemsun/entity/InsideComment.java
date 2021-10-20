package com.bluemsun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

//评论的回复评论区
public class InsideComment {
    private int id;
    private String content;//评论内容
    private int commentMainId;//所回复的评论id
    private Timestamp createTime;//评论创建时间
    private int userId;//评论的用户id
    private int likesNum;//点赞数
    private int isMy;

    public InsideComment() {
    }

    public InsideComment(int id, String content, int commentMainId, Timestamp createTime, int userId, int likesNum, int isMy) {
        this.id = id;
        this.content = content;
        this.commentMainId = commentMainId;
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

    public int getCommentMainId() {
        return commentMainId;
    }

    public void setCommentMainId(int commentMainId) {
        this.commentMainId = commentMainId;
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
        return "InsideComment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", commentMainId=" + commentMainId +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", likesNum=" + likesNum +
                ", isMy=" + isMy +
                '}';
    }
}
