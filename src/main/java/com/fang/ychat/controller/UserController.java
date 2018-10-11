package com.fang.ychat.controller;

import com.fang.ychat.dao.RelationMapper;
import com.fang.ychat.pojo.Relation;
import com.fang.ychat.pojo.User;
import com.fang.ychat.service.RelationService;
import com.fang.ychat.service.UserService;
import com.fang.ychat.vo.BaseUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RelationService relationService;

    /**
     * 登录认证
     * @param request 通过request获取到用户名
     * @param model  添加数据
     * @return  返回到chat
     */
    @PostMapping("/login")
    public String login(HttpServletRequest request ,Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.selectByUsername(username);

            model.addAttribute("user",user);

        //通过subect来实现认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try{
            subject.login(usernamePasswordToken);
        }catch(UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "login";}
        catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }

        List<BaseUser> friends = relationService.getFriendsByUsername(username);
        System.out.println(friends+"****");
        model.addAttribute("friends",friends);
        return "chat";
    }
    //注册
    @PostMapping("/register")
    @ResponseBody
    public boolean register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        User user = new User(username,password,nickname);
        if(null != userService.selectByUsername(user.getUsername())){
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(dateFormat.format(new Date()));
        return userService.add(user);
    }

    /**
     * 添加朋友
     * @param principal 用于获取到当前用户
     * @param friend
     * @return  返回一个字符，判断添加是否成功
     */
    @PostMapping("/addfriend")
    @ResponseBody
    public String addfriend(Principal principal, @RequestParam String friend){
//        System.out.println("-----------"+relationService.addFriend(principal.getName(),friend)+"+++++++++++++");
        return relationService.addFriend(principal.getName(),friend);
    }
}
