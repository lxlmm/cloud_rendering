package com.info_system.entity;

public class Message {
    private int messageId;
    private int userId;
    private int senderId;
    private String senderName;
    private int messageType;
    private String messageContent;
    private int blogId;
    private String blogName;

    public Message() {
    }

    public Message(int userId, int senderId, int messageType) {
        this.userId = userId;
        this.senderId = senderId;
        this.messageType = messageType;
    }

    public Message(int userId, int senderId, int messageType, int blogId) {
        this.userId = userId;
        this.senderId = senderId;
        this.messageType = messageType;
        this.blogId = blogId;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", userId=" + userId +
                ", senderId=" + senderId +
                ", senderName='" + senderName + '\'' +
                ", messageType=" + messageType +
                ", messageContent='" + messageContent + '\'' +
                ", blogId=" + blogId +
                '}';
    }
}
