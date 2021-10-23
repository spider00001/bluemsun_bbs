package com.bluemsun.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class PlateApplicationDto {

    private int id;
    private String content;
    private String plateName;
    private int userId;
    private Timestamp createTime;
    private int status;
    private String username;//用户名
    private String headPortrait;//头像资源路径

    public PlateApplicationDto() {
    }

    public PlateApplicationDto(int id, String content, String plateName, int userId, Timestamp createTime, int status, String username, String headPortrait) {
        this.id = id;
        this.content = content;
        this.plateName = plateName;
        this.userId = userId;
        this.createTime = createTime;
        this.status = status;
        this.username = username;
        this.headPortrait = headPortrait;
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

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "PlateApplicationDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", plateName='" + plateName + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                '}';
    }
}
