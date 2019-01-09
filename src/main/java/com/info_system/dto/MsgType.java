package com.info_system.dto;

public enum MsgType {
    Success(200,"操作成功"),
    Error(400,"操作失败");
    MsgType(int code,String msgName)
    {
        this.code=code;
        this.msgName=msgName;
    }

    private int code;

    private String msgName;
}
