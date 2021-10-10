package com.bluemsun.entity;

import java.sql.Timestamp;

public class PlateApplication {
    private int id;
    private String content;
    private String plateName;
    private int userId;
    private Timestamp createTime;
    private int status;

    public PlateApplication() {
    }

    public PlateApplication(int id, String content, String plateName, int userId, Timestamp createTime, int status) {
        this.id = id;
        this.content = content;
        this.plateName = plateName;
        this.userId = userId;
        this.createTime = createTime;
        this.status = status;
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

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PlateApplication{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", plateName='" + plateName + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
