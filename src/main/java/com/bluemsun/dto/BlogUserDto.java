package com.bluemsun.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class BlogUserDto {

    private int id;// 博客id
    private int userId;// 用户id
    private String username;// 用户名
    private String title;// 标题
    private Timestamp createTime;// 创建时间(最后一次修改时间)
    private int views;// 浏览量
    private String content;// 内容
    private int likesNum;// 点赞数
    private String headPortrait;// 头像资源路径
    private int blogType;// 板块类型 0文章 1资源下载
    private String plateName;// 所属板块名称
    private int plateId;// 所属板块id

    public int getBlogType() {
        return blogType;
    }

    public void setBlogType(int blogType) {
        this.blogType = blogType;
    }

    public BlogUserDto() {
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public int getPlateId() {
        return plateId;
    }

    public void setPlateId(int plateId) {
        this.plateId = plateId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(int likesNum) {
        this.likesNum = likesNum;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public String toString() {
        return "BlogUserDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", views=" + views +
                ", content='" + content + '\'' +
                ", likesNum=" + likesNum +
                ", headPortrait='" + headPortrait + '\'' +
                ", blogType=" + blogType +
                ", plateName='" + plateName + '\'' +
                ", plateId=" + plateId +
                '}';
    }
}
