package com.info_system.dao;

import com.info_system.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /**
     * 验证用户名
     * @param username
     */
    public int checkUser(String username);

    /**
     * 登录检查
     * @param username
     */
    public String checkLogin(String username);

    /**
     * 增加新用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 通讯录
     * @param username
     */
    public List<User> listMsg(String username);

    public User getUserByName(String userName);

    public User getUserById(int id);
}
