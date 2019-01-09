package com.info_system.dao;

import com.info_system.entity.Blog;
import com.info_system.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BanUserDao {
    List<User> listUser();
    List<Blog> listBlog();
    void changeUser(User user);
    void changeBlog(Blog blog);
    int getUserIdByBlogId(int blogId);
    List<Blog> getUserAllBlogs(int userId);
    void delBan(@Param("admin_id") int admin_id, @Param("user_id") int user_id );
    void createBan(@Param("admin_id") int admin_id,@Param("user_id") int user_id );
    void delDel(@Param("admin_id") int admin_id,@Param("blog_id") int blog_id );
    void createDel(@Param("admin_id") int admin_id,@Param("blog_id") int blog_id );
}
