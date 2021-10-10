package com.bluemsun.entity;

import java.sql.Timestamp;

public class ManagerNotice {
    private int id;
    private String content;
    private Timestamp createTime;
    private int form;
    private String title;

    public ManagerNotice() {
    }

    public ManagerNotice(int id, String content, Timestamp createTime, int form, String title) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.form = form;
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

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ManagerNotice{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", form=" + form +
                ", title='" + title + '\'' +
                '}';
    }
}
