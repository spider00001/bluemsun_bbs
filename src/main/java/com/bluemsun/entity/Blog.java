package com.bluemsun.entity;

import java.sql.Timestamp;

public class Blog {

    private int id;
    private int userId;//用户id
    private String title;//标题
    private Timestamp createTime;//创建时间(最后一次修改时间)
    private int views;//浏览量
    private String storagePath;//文章存储路径
    private int releaseForm;//发布形式(0:公开 1:私密)
    private int likesNum;//点赞数

    public Blog() {
    }

    public Blog(int id, int userId, String title, Timestamp createTime, int views, String storagePath, int releaseForm, int likesNum) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.createTime = createTime;
        this.views = views;
        this.storagePath = storagePath;
        this.releaseForm = releaseForm;
        this.likesNum = likesNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public int getReleaseForm() {
        return releaseForm;
    }

    public void setReleaseForm(int releaseForm) {
        this.releaseForm = releaseForm;
    }

    public int getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(int likesNum) {
        this.likesNum = likesNum;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", views=" + views +
                ", storagePath='" + storagePath + '\'' +
                ", releaseForm=" + releaseForm +
                ", likesNum=" + likesNum +
                '}';
    }
}
