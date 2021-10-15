package com.bluemsun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Plate {
    private int id;
    private String plateName;
    private int userId;
    private String username;
    private Timestamp createTime;
    private int status;
    private int blogNum;
    private String description;

    public Plate() {
    }

    public Plate(int id, String plateName, int userId, Timestamp createTime, int status, int blogNum, String description) {
        this.id = id;
        this.plateName = plateName;
        this.userId = userId;
        this.createTime = createTime;
        this.status = status;
        this.blogNum = blogNum;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(int blogNum) {
        this.blogNum = blogNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "id=" + id +
                ", plateName='" + plateName + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", status=" + status +
                ", blogNum=" + blogNum +
                ", description='" + description + '\'' +
                '}';
    }
}
