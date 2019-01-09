package com.info_system.controller;

import com.info_system.dto.AjaxMessage;
import com.info_system.dto.MsgType;
import com.info_system.entity.*;
import com.info_system.service.BlogService;
import com.info_system.service.UserService;
import com.info_system.utils.FileUtils;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.Collator;
import java.util.*;

@Controller
@RequestMapping(value = "/info_system")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sendBlog")
    public String sendBlog(HttpSession session, HttpServletRequest request)
    {
        return "sendBlog";
    }

    @RequestMapping(value = "/home")
    public String home(HttpSession session, HttpServletRequest request)
    {
        return "home";
    }

    @RequestMapping(value="/myBlogs")
    public String myBlog(HttpSession session,HttpServletRequest request){
        return "myBlogs";
    }

    @RequestMapping(value="/myComment")
    public String myComment(HttpServletRequest request,HttpSession session){
        return "myComment";
    }

    @RequestMapping(value="/imgImport")
    public String imgImport(HttpServletRequest request){
        return "imgImport";
    }

    @RequestMapping(value="/addBlog")
    @ResponseBody
    public Object addBlog(HttpServletRequest request, Model model, HttpSession session){
        return blogService.addBlog(session,request);
    }

    @RequestMapping(value="/setImgImport")
    @ResponseBody
    public Object setImgImport(HttpServletRequest request,HttpSession session,Model model){
        User user=(User) session.getAttribute("userSession");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file=multipartRequest.getFile("uploadFile");
        String fileName="";
        if(file!=null)
        {
            String directory="/upload/Avatar/"+user.getId()+"/";
            fileName= FileUtils.writeToServer(request,directory,file);
        }

        if(blogService.changeAvatar(fileName,user.getId())>=0){
            user.setUserPic(fileName);
            return new AjaxMessage().Set(MsgType.Success,"修改成功",user);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/otherBlogs")
    public String otherBlogs(Model model,@RequestParam("userId") int userId){
        model.addAttribute("userId",userId);
        User user=userService.getUserById(userId);
        if(user.getDeleteFlag()==0){
            return "blackUser";
        }
        return "otherBlogs";
    }

    @RequestMapping(value="/getOtherBlogs")
    @ResponseBody
    public Object getOtherBlogs(@RequestParam("userId") int userId,HttpSession session){
        User mainUser=(User)session.getAttribute("userSession");
        int mainId=mainUser.getId();
        User user=blogService.getDetailUserById(userId);
        List<Blog> blogList=blogService.getMyAllBlogs(userId);

        //判断该用户是否已被当前用户关注
        int focusNum=blogService.getFocucNum(userId,mainId);
        HashMap<String,Object> map=new HashMap<String, Object>();
        boolean hasFocus=false;
        if(user!=null){
            map.put("user",user);
            map.put("blogList",blogList);
            map.put("mainId",mainId);
            if(focusNum>0){
                hasFocus=true;
            }
            map.put("hasFocus",hasFocus);
            return new AjaxMessage().Set(MsgType.Success,map);
        }

        return new AjaxMessage().Set(MsgType.Error,null);

    }

    @RequestMapping(value="/addFocus")
    @ResponseBody
    public Object addFocus(HttpServletRequest request){
        int mainId=Integer.parseInt(request.getParameter("mainId"));
        int followerId=Integer.parseInt(request.getParameter("followerId"));
        if(blogService.addFocus(mainId,followerId)>=0){
            return new AjaxMessage().Set(MsgType.Success,null);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/getAllBlogs")
    @ResponseBody
    public Object getAllBlogs(HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int userId=user.getId();
        List<Blog> timeBlogList=blogService.getAllBlogs(userId);
        for(int i=0;i<timeBlogList.size();i++){
            int blogId=timeBlogList.get(i).getBlogId();
            if(blogService.getLikeNumByUser(blogId,userId)>0){
                timeBlogList.get(i).setHasLike(true);
            }else{
                timeBlogList.get(i).setHasLike(false);
            }
            if(blogService.getCommentNumByUser(blogId,userId)>0){
                timeBlogList.get(i).setHasComment(true);
            }else{
                timeBlogList.get(i).setHasComment(false);
            }
        }
        if(timeBlogList.size()>=0){
            List<Blog> hotBlogList=new ArrayList<Blog>();
            for(int i=0;i<timeBlogList.size();i++){
                hotBlogList.add(timeBlogList.get(i));
            }
            Collections.sort(hotBlogList);

            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("hotBlogList",hotBlogList);
            map.put("timeBlogList",timeBlogList);
            return new AjaxMessage().Set(MsgType.Success,map);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/blogDetail")
    public String  getBlogDetail(@RequestParam("blogId") int blogId,Model model,HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int flag=user.getDeleteFlag();
        boolean commentFlag=true;
        if(flag==1){
            commentFlag=false;
        }
        model.addAttribute("commentFlag",commentFlag);
        model.addAttribute("nowBlogId",blogId);
        return "blogDetail";
    }

    @RequestMapping(value="/getBlogItem")
    @ResponseBody
    public Object getBlogItem(@RequestParam("blogId") int blogId,HttpSession session){
        Blog blog=blogService.getBlogById(blogId);
        List<Comment> commentList=blogService.getCommentByBlogId(blogId);
        User user=(User) session.getAttribute("userSession");
        int  userId=user.getId();
        int fucosNum=blogService.getFocucNum(userId,blog.getUser().getId());
        boolean hasFocus=false;
        if(fucosNum>0){
            //已关注
            hasFocus=true;
        }
        for(int i=0;i<commentList.size();i++){
            if(blogService.getCommentLikeCount(commentList.get(i).getCommentId())>0){
                commentList.get(i).setHasComment(true);
            }else{
                commentList.get(i).setHasComment(false);
            }
        }
        if(blogService.getLikeNumByUser(blogId,userId)>0){
            blog.setHasLike(true);
        }else{
            blog.setHasLike(false);
        }
        if(blogService.getCommentNumByUser(blogId,userId)>0){
            blog.setHasComment(true);
        }else{
            blog.setHasComment(false);
        }
        HashMap<String ,Object> map=new HashMap<String, Object>();
        map.put("blog",blog);
        map.put("commentList",commentList);
        map.put("userId",userId);
        map.put("hasFocus",hasFocus);
        if(map!=null){
            return new AjaxMessage().Set(MsgType.Success,map);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/addMyComment")
    @ResponseBody
    public Object addMyComment(HttpSession session,HttpServletRequest request){
        User user=(User)session.getAttribute("userSession");
        int userId=user.getId();
        String content=request.getParameter("commentContent");
        int blogId=Integer.parseInt(request.getParameter("blogId"));
        Date commentTime=new Date();
        Comment comment=new Comment();

        comment.setBlogId(blogId);
        comment.setCommentContent(content);
        comment.setUserId(userId);
        comment.setCommentTime(commentTime);

        int commentId=blogService.addMyComment(comment);
        commentId=comment.getCommentId();
        if(commentId>=0){
            Comment newCom=blogService.getCommentByCommentId(commentId);
            return new AjaxMessage().Set(MsgType.Success,"提交成功",newCom);
        }
        return new AjaxMessage().Set(MsgType.Error,"添加失败",null);
    }

    @RequestMapping(value="/deleteCommentLike")
    @ResponseBody
    public Object deleteCommentLike(HttpSession session,HttpServletRequest request){
        int commentId=Integer.parseInt(request.getParameter("commentId"));
        User user=(User) session.getAttribute("userSession");
        int userId=user.getId();
        if(blogService.deleteCommentByCommentAndUser(commentId,userId)>=0){
            return new AjaxMessage().Set(MsgType.Success,null);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/addCommentLike")
    @ResponseBody
    public Object addCommentLike(HttpServletRequest request,HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int userId=user.getId();
        int commentId=Integer.parseInt(request.getParameter("commentId"));
        LikeComment likeComment=new LikeComment();
        likeComment.setCommentId(commentId);
        likeComment.setUserId(userId);
        if(blogService.insertCommentLikeByUserAndComment(likeComment)>=0){
            return new AjaxMessage().Set(MsgType.Error,null);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/addBlogLike")
    @ResponseBody
    public Object addBlogLike(HttpSession session,HttpServletRequest request){
        User user=(User) session.getAttribute("userSession");
        int userId=user.getId();
        int blogId=Integer.parseInt(request.getParameter("blogId"));
        LikeBlog likeBlog=new LikeBlog();
        likeBlog.setBlogId(blogId);
        likeBlog.setUserId(userId);
        if(blogService.addBlogLike(likeBlog)>=0){
            return new AjaxMessage().Set(MsgType.Success,null);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/deleteBlogLike")
    @ResponseBody
    public Object deleteBlogLike(HttpServletRequest request,HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int userId=user.getId();
        int blogId=Integer.parseInt(request.getParameter("blogId"));
        if(blogService.deleteBlogLike(blogId,userId)>=0){
            return new AjaxMessage().Set(MsgType.Success,null);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/getMyBlogList")
    @ResponseBody
    public Object getMyBlogList(HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int userId=user.getId();
        List<Blog> timeBlogList=blogService.getMyAllBlogs(userId);
        for(int i=0;i<timeBlogList.size();i++){
            int blogId=timeBlogList.get(i).getBlogId();
            if(blogService.getLikeNumByUser(blogId,userId)>0){
                timeBlogList.get(i).setHasLike(true);
            }else{
                timeBlogList.get(i).setHasLike(false);
            }
            if(blogService.getCommentNumByUser(blogId,userId)>0){
                timeBlogList.get(i).setHasComment(true);
            }else{
                timeBlogList.get(i).setHasComment(false);
            }
        }
        if(timeBlogList.size()>=0){
            List<Blog> hotBlogList=new ArrayList<Blog>();
            for(int i=0;i<timeBlogList.size();i++){
                hotBlogList.add(timeBlogList.get(i));
            }
            Collections.sort(hotBlogList);

            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("hotBlogList",hotBlogList);
            map.put("timeBlogList",timeBlogList);
            return new AjaxMessage().Set(MsgType.Success,map);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/updateDeleteFlag")
    @ResponseBody
    public Object updateDeleteFlag(HttpServletRequest request){
        String deleteFlag=request.getParameter("deleteFlag");
        int blogId=Integer.parseInt(request.getParameter("blogId"));
        int flag=0;
        boolean flagB;
        if(deleteFlag.equals("true")){
            flag=1;
            flagB=true;
        }else{
            flag=0;
            flagB=false;
        }
        if(blogService.updateDeleteFlag(flag,blogId)>=0){
           return new AjaxMessage().Set(MsgType.Success,flagB);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/getMyComments")
    @ResponseBody
    public Object getMyComments(HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int userId=user.getId();
        List<Comment> commentList=blogService.getMyComments(userId);
        if(commentList.size()>=0){
            return new AjaxMessage().Set(MsgType.Success,commentList);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value="/updateCommentDeleteFlag")
    @ResponseBody
    public Object updateCommentDeleteFlag(HttpSession session,HttpServletRequest request){
        String deleteFlag=request.getParameter("deleteFlag");
        int commentId=Integer.parseInt(request.getParameter("commentId"));
        int flag=0;
        boolean flagB;
        if(deleteFlag.equals("true")){
            flag=1;
            flagB=true;
        }else{
            flag=0;
            flagB=false;
        }
        if(blogService.updateCommentDeleteFlag(flag,commentId)>=0){
            return new AjaxMessage().Set(MsgType.Success,flagB);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }


    @RequestMapping(value="/getAllUserList")
    @ResponseBody
    public Object getAllUserList(HttpServletRequest request){
        List<User> userList=blogService.getAllUser();
        if(userList.size()>=0){
            return new AjaxMessage().Set(MsgType.Success,userList);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        File dir = new File(path, fileName);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(dir);
        return "ok!";
    }


    @RequestMapping("/down")
    public String down(HttpServletRequest request, HttpServletResponse response, @RequestParam("blogId") String blogIdStr) throws Exception{
        int blogId = Integer.parseInt(blogIdStr);
        System.out.println("download in controller");
        Blog blog = blogService.getBlogById(blogId);
//        String path = request.getContextPath();
//        String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path ;
        //模拟文件，myfile.txt为需要下载的文件
        String fileName = request.getSession().getServletContext().getRealPath(blog.getBlogPic());
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //假如以中文名下载的话
        String filename = blog.getBlogTitle()+".png";
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
        return fileName;
    }



    @RequestMapping("/download")
    public void download(HttpServletRequest request,HttpServletResponse response) throws Exception {
        System.out.println("download in controller");
        //模拟文件，myfile.txt为需要下载的文件
        String fileName = request.getSession().getServletContext().getRealPath("upload") + "/考试.png";
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //假如以中文名下载的话
        String filename = "考试安排.png";
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename, "UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while ((len = bis.read()) != -1) {
            out.write(len);
            out.flush();
        }
        out.close();
    }
}