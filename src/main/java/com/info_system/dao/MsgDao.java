package com.info_system.dao;

import com.info_system.entity.Msg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgDao {

    /**
     * 增加新邮件
     * @param msg
     */
    public void addMsg(Msg msg);

    /**
     * 增加新邮件
     * @param msg
     */
    public void updateMsg(Msg msg);

    /**
     * 收件箱邮件
     * @param username
     */
    public List<Msg> receiveMsg(String username);

    /**
     * 发件箱邮件
     * @param username
     */
    public List<Msg> sendMsg(String username);

    /**
     * 草稿箱邮件
     * @param username
     */
    public List<Msg> draftMsg(String username);

    /**
     * 单个邮件
     * @param id
     */
    public Msg getMsg(int id);

    /**
     * 已读邮件
     * @param id
     */
    public void readMsg(int id);

}
