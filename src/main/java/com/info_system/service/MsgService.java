package com.info_system.service;

import com.info_system.dao.MsgDao;
import com.info_system.entity.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgService {
    @Autowired
    private MsgDao msgDao;

    public MsgService() {
    }

    /**
     * 添加邮件
     * @param msg
     */
    public void addMsg(Msg msg) {
        System.out.println("addMsg in MsgService");
        msgDao.addMsg(msg);
    }

    /**
     * 更新邮件
     * @param msg
     */
    public void updateMsg(Msg msg) {
        System.out.println("updateMsg in MsgService");
        msgDao.updateMsg(msg);
    }

    /**
     * 收件箱邮件
     * @param username
     */
    public List<Msg> receiveMsg(String username) {
        System.out.println("receiveMsg in MsgService");
        return msgDao.receiveMsg(username);
    }

    /**
     * 发件箱邮件
     * @param username
     */
    public List<Msg> sendMsg(String username) {
        System.out.println("sendMsg in MsgService");
        return msgDao.sendMsg(username);
    }

    /**
     * 草稿箱邮件
     * @param username
     */
    public List<Msg> draftMsg(String username) {
        System.out.println("draftMsg in MsgService");
        return msgDao.draftMsg(username);
    }

    /**
     * 单个邮件
     * @param id
     */
    public Msg getMsg(int id) {
        System.out.println("getMsg in MsgService");
        return msgDao.getMsg(id);
    }

    /**
     * 已读邮件
     * @param id
     */
    public void readMsg(int id) {
        System.out.println("readMsg in MsgService");
        msgDao.readMsg(id);
    }

}
