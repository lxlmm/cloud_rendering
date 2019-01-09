package com.info_system.controller;

import com.info_system.dto.AjaxMessage;
import com.info_system.dto.MsgType;
import com.info_system.entity.Blog;
import com.info_system.entity.Follow;
import com.info_system.entity.User;
import com.info_system.service.BlogService;
import com.info_system.service.FollowService;
import com.info_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/info_system")
public class FollowController {
    @Autowired
    FollowService followService;

    @RequestMapping(value = "/focusPer", method = RequestMethod.GET)
    public String focusPer() {
        return "focusPer";
    }

    @RequestMapping(value = "/focusMe", method = RequestMethod.GET)
    public String focusMe() {
        return "focusMe";
    }

//    @RequestMapping(value = "/addFollow", method = RequestMethod.GET)
//    public String addFollow() {
//        return "addFollow";
//    }

    @RequestMapping(value = "/getFollowList")
    @ResponseBody
    public Object getFollowList(HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int userId = user.getId();
        List<Follow> listFollow=followService.listFollow(userId);
        if (listFollow.size()>=0) {
            return new AjaxMessage().Set(MsgType.Success,listFollow);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value = "/getFansList")
    @ResponseBody
    public Object getFansList(HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int userId = user.getId();
        List<Follow> listFans=followService.listFans(userId);
        if (listFans.size()>=0) {
            return new AjaxMessage().Set(MsgType.Success,listFans);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }

    @RequestMapping(value = "/deleteFollow")
    public String deleteFollow(@RequestParam("followId") int followId) {
        followService.deleteFollow(followId);
        return "focusPer";
    }

    @RequestMapping(value = "/addFollowUser")
    public String addFollowUser(
            @RequestParam("followId") int userId,
            HttpSession session
    ){
        User user=(User) session.getAttribute("userSession");
        int followerId = user.getId();
        Follow follow = new Follow(followerId, userId);
        followService.addFollow(follow);
        return "addFollow";
    }

    @RequestMapping(value = "/addFollow")
    public String addFollow(
            @RequestParam("username") String username,
            HttpSession session,
            Model model
    ){
        model.addAttribute("userName",username);
        return "addFollow";
    }

    @RequestMapping(value = "/getUnFollowList")
    @ResponseBody
    public Object getUnFollowList(HttpSession session,
            @RequestParam("username") String username
    ) {
        User user=(User) session.getAttribute("userSession");
//      获取没有关注的用户
        List<Follow> listUnFollow=followService.listUnFollow(username,user.getId());
        for(int i=0;i<listUnFollow.size();i++) {
            System.out.println(listUnFollow.get(i).getUsername());
        }
        if (listUnFollow.size()>=0) {
            return new AjaxMessage().Set(MsgType.Success,listUnFollow);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }
}

