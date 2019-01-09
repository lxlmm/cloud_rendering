package com.info_system.entity;

public class Follow {
    private int followId;
    private int userId;
    private String username;
    private int blogNum;
    private int fansNum;
    private int followNum;
    private int followerId;

    @Override
    public String toString() {
        return "Follow{" +
                "followId=" + followId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", blogNum=" + blogNum +
                ", fansNum=" + fansNum +
                ", followNum=" + followNum +
                ", followerId=" + followerId +
                '}';
    }

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
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

    public int getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(int blogNum) {
        this.blogNum = blogNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public Follow(int followId, int userId, String username, int blogNum, int fansNum, int followNum, int followerId) {
        this.followId = followId;
        this.userId = userId;
        this.username = username;
        this.blogNum = blogNum;
        this.fansNum = fansNum;
        this.followNum = followNum;
        this.followerId = followerId;
    }

    public Follow() {
    }

    public Follow(int followerId, int userId) {
        this.followerId = followerId;
        this.userId = userId;
    }
}
