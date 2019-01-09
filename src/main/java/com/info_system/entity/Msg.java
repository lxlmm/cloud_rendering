package com.info_system.entity;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Msg {
    int id;
    String receiver;
    String sender;
    String theme;
    String content;
    String attachment;  //附件
    String time;          //发送日期
    boolean status;     //0未发，1已发
    boolean isRead;     //0未读，1已读

    public Msg() {
    }

    public Msg(String receiver, String sender, String theme, String content, String attachment, boolean status) {
        this.receiver = receiver;
        this.sender = sender;
        this.theme = theme;
        this.content = content;
        this.attachment = attachment;
        setTime();
        this.status = status;
    }

    public Msg(int id, String receiver, String sender, String theme, String content, String attachment, boolean status) {
        this.id = id;
        this.receiver = receiver;
        this.sender = sender;
        this.theme = theme;
        this.content = content;
        this.attachment = attachment;
        setTime();
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getTime() {
        return time;
    }

    public void setTime() {
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(currentTime);
        this.time = dateString;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
