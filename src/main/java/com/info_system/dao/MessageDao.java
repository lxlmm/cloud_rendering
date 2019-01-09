package com.info_system.dao;

import com.info_system.entity.Message;

import java.util.List;

public interface MessageDao {
    List<Message> listMessage(int userId);
    void addMessage(Message message);

}
