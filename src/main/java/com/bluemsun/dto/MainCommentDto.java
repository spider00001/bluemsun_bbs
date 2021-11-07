package com.bluemsun.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class MainCommentDto {
    private int id;//评论id
    private String content;//评论内容
    private int blogId;//博客id
    private Timestamp createTime;//评论创建时间
    private int userId;//评论的用户id
    private String username;//评论的用户名
    private String headPortrait;//头像
    private int likesNum;//点赞数
    private int isMy;

    public MainCommentDto() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
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
        return "MainCommentDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", blogId=" + blogId +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", likesNum=" + likesNum +
                ", isMy=" + isMy +
                '}';
    }
}
