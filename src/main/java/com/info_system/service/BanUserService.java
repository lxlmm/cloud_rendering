package com.info_system.service;

import com.info_system.dao.BanUserDao;
import com.info_system.dao.UserDao;
import com.info_system.entity.Blog;
import com.info_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanUserService {

    @Autowired
    private BanUserDao banUserDao;

    public List<User> listUser() {
        System.out.println("listUser in BanUserService");
        return banUserDao.listUser();
    }

    public List<Blog> listBlog() {
        System.out.println("listBlog in BanUserService");
        return banUserDao.listBlog();
    }
    public List<Blog> getUserAllBlogs(int userId) {
        System.out.println("getUserAllBlogs in BanUserService");
        return banUserDao.getUserAllBlogs(userId);
    }
    /**
     *
     * @param user
     */
    public void changeUser(User user) {
        System.out.println("changeUser in BanUserService");
        banUserDao.changeUser(user);
    }

    /**
     *
     * @param blog
     */
    public void changeBlog(Blog blog) {
        System.out.println("changeBlog in BanUserService");
        banUserDao.changeBlog(blog);
    }

    public int getUserIdByBlogId(int blogId) {
        System.out.println("getUserIdByBlogId in BanUserService");
        return banUserDao.getUserIdByBlogId(blogId);
    }

    public void delBan(int admin_id,int user_id ) {
        System.out.println("delBan in BanUserService");
        banUserDao.delBan(admin_id,user_id);
    }
    public void createBan(int admin_id,int user_id ) {
        System.out.println("createBan in BanUserService");
        banUserDao.createBan(admin_id,user_id);
    }
    public void delDel(int admin_id,int blog_id ) {
        System.out.println("delDel in BanUserService");
        banUserDao.delDel(admin_id,blog_id);
    }
    public void createDel(int admin_id,int blog_id ) {
        System.out.println("createDel in BanUserService");
        banUserDao.createDel(admin_id,blog_id);
    }
}
