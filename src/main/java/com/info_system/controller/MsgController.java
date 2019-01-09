package com.info_system.controller;

import com.info_system.entity.Msg;
import com.info_system.entity.User;
import com.info_system.service.MsgService;
import com.info_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class MsgController {
    @Autowired
    MsgService msgService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(
            @RequestParam("receiver") String receiver,
            @RequestParam("theme") String theme,
            @RequestParam("content") String content,
            @RequestParam("attachment") String attachment,
            @RequestParam("sender") String sender,
            @RequestParam("status") boolean status,
            @RequestParam("submit") String submit,
            Model model
    ) {
        System.out.println("MsgController，写信");
        if (submit.equals("send")) status=true;
        else status=false;
        if(receiver=="") receiver="(未添加收信人)";
        if(theme=="") theme="(未添加主题)";
        if(content=="") content="(未添加内容)";
        if(attachment=="") attachment="(未添加附件)";
        Msg msg = new Msg(receiver, sender, theme, content, attachment, status);
        msgService.addMsg(msg);
        model.addAttribute("username", sender);
//        model.addAttribute("flag", "write");
        return "homepage";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam("id") int id,
            @RequestParam("receiver") String receiver,
            @RequestParam("theme") String theme,
            @RequestParam("content") String content,
            @RequestParam("attachment") String attachment,
            @RequestParam("sender") String sender,
            @RequestParam("status") boolean status,
            @RequestParam("submit") String submit,
            Model model
    ) {
        System.out.println(receiver);
        System.out.println(theme);
        System.out.println(content);
        System.out.println("MsgController，更新邮件");
        if (submit.equals("send")) status=true;
        else status=false;
        if(receiver=="") receiver="(未添加收信人)";
        if(theme=="") theme="(未添加主题)";
        if(content=="") content="(未添加内容)";
        if(attachment=="") attachment="(未添加附件)";
        Msg msg = new Msg(id, receiver, sender, theme, content, attachment, status);
        System.out.println(msg.getId());
        System.out.println(msg.getReceiver());
        System.out.println(msg.getTheme());
        System.out.println(msg.getContent());
        msgService.updateMsg(msg);
        model.addAttribute("username", sender);
//        model.addAttribute("flag", "write");
        return "homepage";
    }

    @RequestMapping(value = "/receiveMsg")
    @ResponseBody
    public List<Msg> receiveMsg(
            @RequestParam("username") String username
    ) {
        System.out.println("MsgController，收件箱");
        List<Msg> receiveList = msgService.receiveMsg(username);
        return receiveList;
    }

    @RequestMapping(value = "/sendMsg")
    @ResponseBody
    public List<Msg> sendMsg(
            @RequestParam("username") String username
    ) {
        System.out.println("MsgController，发件箱");
        List<Msg> sendList = msgService.sendMsg(username);
        return sendList;
    }

    @RequestMapping(value = "/draftMsg")
    @ResponseBody
    public List<Msg> draftMsg(
            @RequestParam("username") String username
    ) {
        System.out.println("MsgController，草稿箱");
        List<Msg> draftList = msgService.draftMsg(username);
        return draftList;
    }

    @RequestMapping(value = "/listMsg")
    @ResponseBody
    public List<User> listMsg(
            @RequestParam("username") String username
    ) {
        System.out.println("MsgController，通讯录");
        List<User> listMsg = userService.listMsg(username);
        return listMsg;
    }

    @RequestMapping(value = "/getMsg")
    @ResponseBody
    public Msg getMsg(
            @RequestParam("id") int id
    ) {
        System.out.println("MsgController，单个邮件");
        msgService.readMsg(id);
        Msg msg = msgService.getMsg(id);
        return msg;
    }
}

