package com.info_system.controller;

import com.info_system.dto.AjaxMessage;
import com.info_system.dto.MsgType;
import com.info_system.entity.Message;
import com.info_system.entity.User;
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
public class MessageController {
    @Autowired
    private MessageService messageService;



    @RequestMapping(value = "/messageInfo", method = RequestMethod.GET)
    public String messageInfo() {
        return "messageInfo";
    }

    @RequestMapping(value = "/getMessageList")
    @ResponseBody
    public Object getMessageList(HttpSession session){
        User user=(User) session.getAttribute("userSession");
        int userId = user.getId();
        List<Message> listMessage= messageService.listMessage(userId);
        if (listMessage.size()>=0) {
            return new AjaxMessage().Set(MsgType.Success,listMessage);
        }
        return new AjaxMessage().Set(MsgType.Error,null);
    }


    @RequestMapping(value = "/addMessage")
    public String addMessage(
            @RequestParam("userId") int userId,
            @RequestParam("messageType") int messageType,
            HttpSession session
    ){
        User user=(User) session.getAttribute("userSession");
        int senderId = user.getId();
        Message message = new Message(userId, senderId, messageType);
        messageService.addMessage(message);
        return "messageInfo";
    }
}
