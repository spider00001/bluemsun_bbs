package com.bluemsun.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class InsideCommentDto {

    private int commentMainId;//所回复的评论id
    private int id;//回复id
    private String content;//回复内容
    private Timestamp createTime;//回复创建时间
    private int userId;//回复的用户id
    private String username;//回复的用户名
    private String headPortrait;//头像
    private int likesNum;//点赞数
    private int isMy;

    public InsideCommentDto() {
    }

    public int getCommentMainId() {
        return commentMainId;
    }

    public void setCommentMainId(int commentMainId) {
        this.commentMainId = commentMainId;
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
}
