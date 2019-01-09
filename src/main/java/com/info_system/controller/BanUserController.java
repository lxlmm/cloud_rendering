package com.info_system.controller;

import com.info_system.dto.AjaxMessage;
import com.info_system.dto.MsgType;
import com.info_system.entity.Blog;
import com.info_system.entity.Message;
import com.info_system.entity.User;
import com.info_system.service.BanUserService;
import com.info_system.service.BlogService;
import com.info_system.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "/info_system")
public class BanUserController {
    @Autowired
    private BanUserService banUserService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/banUser",method = RequestMethod.GET)
    public String banUser() {
        return "banUser";
    }

    @RequestMapping(value = "/deletedBlog",method = RequestMethod.GET)
    public String deletedBlog() {
        return "deletedBlog";
    }

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public Object getUserList(HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int userId = user.getId();
        List<User> listUser=banUserService.listUser();
        if (listUser.size()>=0) {
            return new AjaxMessage().Set(MsgType.Success,listUser);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value = "/getBlogList")
    @ResponseBody
    public Object getBlogList(HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int userId = user.getId();
        List<Blog> listBlog=banUserService.listBlog();
        for(int i=0;i<listBlog.size();i++){
            if(listBlog.get(i).getDeleteFlag()==0){
                listBlog.get(i).setDeleteFlag(1);
            }
        }
        if (listBlog.size()>=0) {
            return new AjaxMessage().Set(MsgType.Success,listBlog);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value = "/changeUser")
    public String changeUser(
            @RequestParam("userId") int userId,
            @RequestParam("flag") int deleteFlag,
            HttpSession session
    ) {
        User sender=(User) session.getAttribute("userSession");
        User user = new User(userId,deleteFlag);
        List<Blog> blogs = banUserService.getUserAllBlogs(userId);
        int msgType;
        if (deleteFlag==1){
            for (int i=0;i<blogs.size();i++){
                blogs.get(i).setDeleteFlag(1);
                banUserService.changeBlog(blogs.get(i));
            }
            msgType=2;
            Message message = new Message(userId,sender.getId(),msgType);
            messageService.addMessageWithoutBlog(message);
            banUserService.delBan(sender.getId(),userId);
        }
        else {
            for (int i=0;i<blogs.size();i++){
                blogs.get(i).setDeleteFlag(2);
                banUserService.changeBlog(blogs.get(i));
            }
            msgType=1;
            Message message = new Message(userId,sender.getId(),msgType);
            messageService.addMessageWithoutBlog(message);
            banUserService.createBan(sender.getId(),userId);
        }
        banUserService.changeUser(user);
        return "banUser";
    }

    @RequestMapping(value = "/changeBlog")
    public String changeBlog(
            @RequestParam("blogId") int blogId,
            @RequestParam("flag") int deleteFlag,
            HttpSession session
    ) {
        User sender=(User) session.getAttribute("userSession");
        int userId = banUserService.getUserIdByBlogId(blogId);
        int msgType;
        if(deleteFlag==1) {
            msgType=5;
            banUserService.createDel(sender.getId(),blogId);
        }
        else{
            msgType=4;
            banUserService.delDel(sender.getId(),blogId);
        }
        Message message = new Message(userId,sender.getId(),msgType,blogId);
        messageService.addMessage(message);
        if (deleteFlag==0)
            deleteFlag=2;
        Blog blog = new Blog(blogId,deleteFlag);
        banUserService.changeBlog(blog);
        return "deletedBlog";
    }

}

