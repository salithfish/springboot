package com.fang.ychat.controller;

import com.fang.ychat.dao.PermissionMapper;
import com.fang.ychat.dao.RoleMapper;
import com.fang.ychat.dao.Role_PermissionMapper;
import com.fang.ychat.dao.User_RoleMapper;
import com.fang.ychat.pojo.Permission;
import com.fang.ychat.pojo.Role;
import com.fang.ychat.pojo.User;
import com.fang.ychat.service.RelationService;
import com.fang.ychat.service.UserService;
import com.fang.ychat.vo.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class pageController {

    @Autowired
    UserService userService;
    @Autowired
    User_RoleMapper user_roleMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    Role_PermissionMapper role_permissionMapper;
    @Autowired
    RelationService relationService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/chat")
    public String chat(){

        return "chat";
    }

//测试权限时用到的代码
//    @GetMapping("/add")
//    public String add(){
//        return "add";
//    }
//    @GetMapping("/info")
//    public String info(){
//        return "info";
//    }
//    @RequestMapping("test")
//    @ResponseBody
//    public String test(){
//        User user = userService.selectByUsername("wxx");
//        System.out.println(user.getNickname());
//        int roleid = user_roleMapper.findUserRoleId(user.getId());
//        Role role = roleMapper.findRole(roleid);
//
//        int permissionid = role_permissionMapper.findPermissionId(role.getId());
//        String permission = permissionMapper.findPermission(permissionid);
//        return permission;
//    }

}
