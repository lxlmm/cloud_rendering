package com.info_system.controller;

import com.info_system.entity.User;
import com.info_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        System.out.println("UserController，进入注册界面...");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("repassword") String repassword,
            @RequestParam("name") String name,
            @RequestParam("sex") String sex,
            Model model
    ) {
        System.out.println("UserController，注册表信息上传...");
        User user = new User(name,sex,username,password);
        if (name.equals("")||sex.equals("")||username.equals("")||password.equals("")||
        name==null|sex==null||username==null||password==null) {
            model.addAttribute("hint", "注册信息不完整，请重新输入");
            return "register";
        } else if (!password.equals(repassword)) {
            model.addAttribute("hint", "两次密码输入不一致");
            return "register";
        } else if (password.length()<6) {
            model.addAttribute("hint", "密码需要大于6位");
            return "register";
        } else if (userService.checkUser(user).equals("username exist")) {
            model.addAttribute("hint", "该用户名已被注册");
            return "register";
        } else {
            userService.addUser(user);
            model.addAttribute("hint", "注册成功");
            return "login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session)
    {
        System.out.println("UserController，登录...");
        int result = userService.loginCheck(username,password);
        if(result==1) {
            User user=userService.getUserByName(username);
            session.setAttribute("userSession",user);
            model.addAttribute("username", username);
            model.addAttribute("userPic",user.getUserPic());
            model.addAttribute("userId",user.getId());
            model.addAttribute("adminFlag",user.getAdminFlag());
            return "homepage";
        }
        else if (result == 2) {
            model.addAttribute("hint", "密码错误");
            return "login";
        } else if (result == 3) {
            model.addAttribute("hint", "用户名错误");
            return "login";
        }else{
            model.addAttribute("hint", "未知错误");
            return "login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        System.out.println("UserController，返回登录界面...");
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        System.out.println("UserController，登出...");
        return "login";
    }
}
/*
@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/getReceive")
    @ResponseBody
    public HashMap<String, Object> getReceive(
            @RequestParam("username") String username
    ) {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        HashMap<String, Object> map = new HashMap<String, ObjeLsct>();

        List<Message> recMessage = messageService.selectReceive(username);
        MessageList messageList = new MessageList();
        Mess mess = new Mess();
        for(Message m : recMessage){
            System.out.println(m);
        }
        for(Message message : recMessage){
            mess.setName(message.getReceiver());
            mess.setTime(message.getTim());
            mess.setTitle(message.getTitle());
            if(message.getStatus().equals("read")){
                mess.setRead(true);
            }
            else mess.setRead(false);
            if(message.getStatus().equals("recycle")){
                messageList.recycleList.add(mess);
            }
            else {
                messageList.receiveList.add(mess);
            }
        }
        map.put("receive", messageList.receiveList);
        map.put("recycle", messageList.recycleList);

        return map;
    }

    @RequestMapping(value = "/getReceive")
    @ResponseBody
    public HashMap<String, Object> getSend(){

    }

}*/
