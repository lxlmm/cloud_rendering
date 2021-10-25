package com.info_system.service;


import com.alibaba.fastjson.JSONObject;
import com.info_system.dao.BlogDao;
import com.info_system.dto.AjaxMessage;
import com.info_system.dto.MsgType;
import com.info_system.entity.*;
import com.info_system.utils.FileUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogDao blogDao;

    public Object addBlog(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("userSession");
        String param = request.getParameter("blog");
        String selectUser = request.getParameter("selectUser");
        JSONArray jsonArray = JSONArray.fromObject(selectUser);
        List<Integer> userList = JSONArray.toList(jsonArray, Integer.class);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("uploadFile");
        String beforeRenderFileName = "";
        String afterRenderFileName = "";
        String previewFileName = "";
        if (file != null) {
            String directory = "D:/ftp/upload/model/" + user.getId() + "/";
            String fileName = FileUtils.writeToServer(request, directory, file);
            beforeRenderFileName = directory  + fileName;
            String[] fileNames=fileName.split("\\.");
            afterRenderFileName = directory  + fileNames[0]+".combine."+fileNames[1];// 渲染后文件格式待定
            previewFileName = directory +  fileNames[0]+".combine."+"png";
        }

        JSONObject obj = JSONObject.parseObject(param);
        Blog blog = JSONObject.toJavaObject(obj, Blog.class);

        if (blog.getBlogId() > 0)//修改商品
        {
            if (file != null) {
                FileUtils.deleteFile(request.getSession().getServletContext().getRealPath("/") + blog.getBeforeBlogPic());
                blog.setBeforeBlogPic(beforeRenderFileName);
            }
            if (blogDao.updateByPrimaryKey(blog) > 0) {
                return new AjaxMessage().Set(MsgType.Success, "商品信息修改成功", null);
            } else {
                return new AjaxMessage().Set(MsgType.Success, "商品信息修改失败", null);
            }
        } else//新增商品
        {
            blog.setBeforeBlogPic(beforeRenderFileName);
            blog.setAfterBlogPic(afterRenderFileName);
            blog.setUser(user);
            Date dateTime = new Date();
            blog.setBlogTime(dateTime);
            blog.setDeleteFlag(0);
            blog.setBlogPreviewPic(previewFileName);
            blogDao.addBlog(blog);
            int blogId = blog.getBlogId();
            if (blogId >= 0) {
                //添加关注的人到数据库
                for (int i = 0; i < userList.size(); i++) {
                    Inform inform = new Inform();
                    inform.setBlogId(blogId);
                    inform.setUserId(userList.get(i));
                    blogDao.addInform(inform);
                    //给每个被@的用户发送一条消息
                    Message message = new Message();
                    message.setMessageType(3);
                    message.setSenderId(user.getId());
                    message.setUserId(userList.get(i));
                    message.setBlogId(blogId);
                    blogDao.addMessage(message);
                }
                return new AjaxMessage().Set(MsgType.Success, "添加成功！", null);
            } else {
                return new AjaxMessage().Set(MsgType.Error, "添加失败", null);
            }
        }
    }

    public List<Blog> getAllBlogs(int userId) {
        return blogDao.getAllBlogs(userId);
    }

    public int getLikeNumByUser(int blogId, int userId) {
        return blogDao.getLikeNumByUser(blogId, userId);
    }

    public int getCommentNumByUser(int blogId, int userId) {
        return blogDao.getCommentNumByUser(blogId, userId);
    }

    public Blog getBlogById(int blogId) {
        return blogDao.getBlogById(blogId);
    }

    public List<Comment> getCommentByBlogId(int blogId) {
        return blogDao.getCommentByBlogId(blogId);
    }

    public int addMyComment(Comment comment) {
        return blogDao.addMyComment(comment);
    }

    public int getCommentLikeCount(int commentId) {
        return blogDao.getCommentLikeCount(commentId);
    }

    public int deleteCommentByCommentAndUser(int commentId, int userId) {
        return blogDao.deleteCommentByCommentAndUser(commentId, userId);
    }

    public int insertCommentLikeByUserAndComment(LikeComment likeComment) {
        return blogDao.insertCommentLikeByUserAndComment(likeComment);
    }

    public Comment getCommentByCommentId(int commentId) {
        return blogDao.getCommentByCommentId(commentId);
    }

    public int addBlogLike(LikeBlog likeBlog) {
        return blogDao.addBlogLike(likeBlog);
    }

    public int deleteBlogLike(int blogId, int userId) {
        return blogDao.deleteBlogLike(blogId, userId);
    }

    public List<Blog> getMyAllBlogs(int userId) {
        return blogDao.getMyAllBlogs(userId);
    }

    public int updateDeleteFlag(int deleteFlag, int blogId) {
        return blogDao.updateDeleteFlag(deleteFlag, blogId);
    }

    public int updateCommentDeleteFlag(int deleteFlag, int commentId) {
        return blogDao.updateCommentDeleteFlag(deleteFlag, commentId);
    }

    public List<Comment> getMyComments(int userId) {
        return blogDao.getMyComments(userId);
    }

    public User getDetailUserById(int userId) {
        return blogDao.getDetailUserById(userId);
    }

    public int getFansNumByUserId(int userId) {
        return blogDao.getFansNumByUserId(userId);
    }

    public int getBlogNumByUserId(int userId) {
        return blogDao.getBlogNumByUserId(userId);
    }

    public List<User> getAllUser() {
        return blogDao.getAllUser();
    }

    public int getFocucNum(int mainId, int followerId) {
        return blogDao.getFocusNum(mainId, followerId);
    }

    public int addFocus(int mainId, int followerId) {
        return blogDao.addFocus(mainId, followerId);
    }


    public int changeAvatar(String userPic, int userId) {
        return blogDao.changeAvatar(userPic, userId);
    }

}
