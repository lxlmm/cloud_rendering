package com.info_system.entity;

import java.util.Date;

public class Comment {
     private int commentId;
    private Blog blog;
    private User user;
    private String commentContent;
    private Date commentTime;
    private int blogId;
    private int userId;
    private boolean hasComment;
    private int commentCount;

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    private  int deleteFlag;


    public boolean isHasComment() {
        return hasComment;
    }

    public void setHasComment(boolean hasComment) {
        this.hasComment = hasComment;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", blog=" + blog +
                ", user=" + user +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", blogId=" + blogId +
                ", userId=" + userId +
                ", hasComment=" + hasComment +
                ", commentCount=" + commentCount +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
