package com.info_system.entity;

public class Inform {
    int informId;
    int userId;
    int blogId;

    public int getInformId() {
        return informId;
    }

    public void setInformId(int informId) {
        this.informId = informId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "informId=" + informId +
                ", userId=" + userId +
                ", blogId=" + blogId +
                '}';
    }
}
