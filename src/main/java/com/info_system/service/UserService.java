package com.info_system.service;

import com.info_system.dao.UserDao;
import com.info_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserService() {

    }
/*
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
*/
    /**
     * 检查username存在
     * @param user
     */
    public String checkUser(User user) {
        System.out.println("CheckUser in UserService");
        int rs = userDao.checkUser(user.getUsername());
        if (rs > 0) {
            return "username exist";
        } else {
            return "success";
        }
    }

    /**
     * 检查登录
     * @param username
     * @param password
     */
    public int loginCheck(String username, String password) {

        System.out.println("loginCheck in UserService");
        int rs =userDao.checkUser(username);
        if(rs==1)
        {
            String pw =userDao.checkLogin(username);
            if(pw.equals(password))
            {
                return 1;
            }
            else return 2;
        }
        else return 3;
    }

    /**
     * 创建新用户
     * @param user
     */
    public void addUser(User user) {
        System.out.println("addUser in UserService");
        userDao.addUser(user);
    }

    /**
     * 通讯录
     * @param username
     */
    public List<User> listMsg(String username) {
        System.out.println("listMsg in UserService");
        return userDao.listMsg(username);

    }

    public User getUserByName(String userName){
        return userDao.getUserByName(userName);
    }

    public User getUserById(int id){
        return userDao.getUserById(id);
    }
}
