package com.info_system.service;

import com.info_system.dao.BlogDao;
import com.info_system.dao.MessageDao;
import com.info_system.entity.Follow;
import com.info_system.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private BlogDao blogDao;

    public List<Message> listMessage(int userId) {
        System.out.println("listMessage in MessageService");
        return messageDao.listMessage(userId);
    }
    public void addMessage(Message message) {
        System.out.println("addMessage in MessageService");
        blogDao.addMessage(message);
    }
    public void addMessageWithoutBlog(Message message) {
        System.out.println("addMessageWithoutBlog in MessageService");
        messageDao.addMessage(message);
    }

}
