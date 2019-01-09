package com.info_system.entity;

public class LikeComment {
    private int commentLikeId;
    private int commentId;
    private int userId;

    public int getCommentLikeId() {
        return commentLikeId;
    }

    public void setCommentLikeId(int commentLikeId) {
        this.commentLikeId = commentLikeId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LikeComment{" +
                "commentLikeId=" + commentLikeId +
                ", commentId=" + commentId +
                ", userId=" + userId +
                '}';
    }
}
