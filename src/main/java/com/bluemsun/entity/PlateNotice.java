package com.bluemsun.entity;

import java.sql.Timestamp;

public class PlateNotice {
    private int id;
    private String content;
    private Timestamp createTime;
    private int plateId;
    private String title;

    public PlateNotice() {
    }

    public PlateNotice(int id, String content, Timestamp createTime, int plateId, String title) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.plateId = plateId;
        this.title = title;
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

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getPlateId() {
        return plateId;
    }

    public void setPlateId(int plateId) {
        this.plateId = plateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "PlateNotice{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", plateId=" + plateId +
                ", title='" + title + '\'' +
                '}';
    }
}
