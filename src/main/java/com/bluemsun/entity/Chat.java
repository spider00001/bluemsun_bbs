package com.bluemsun.entity;

import java.sql.Timestamp;

public class Chat {
    private int id;
    private Timestamp createTime;//创建时间
    private int userId;//用户id
    private int friendId;//好友Id(user)
    private int status;//状态(0: 未读 1:已读)

    public Chat() {
    }

    public Chat(int id, Timestamp createTime, int userId, int friendId, int status) {
        this.id = id;
        this.createTime = createTime;
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", friendId=" + friendId +
                ", status=" + status +
                '}';
    }
}
