package com.fang.ychat.service;

import com.fang.ychat.dao.RelationMapper;
import com.fang.ychat.pojo.User;
import com.fang.ychat.vo.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelationService {
    @Autowired
    UserService userService;
    @Autowired
    RelationMapper relationMapper;
    public String addFriend(String username, String friend){
        if(null == userService.selectByUsername(friend)){
            System.out.println("用户不存在");
            return "用户不存在！";
        }
        if(username.equals(friend)){
            System.out.println("不能添加本人!");
            return "不能添加本人！";
        }
        if(relationMapper.getFriendsByUsername(username)!=null&&relationMapper.getFriendsByUsername(username).contains(friend)){
            System.out.println("已是好友");
            return "已是好友！";
        }
        relationMapper.addFriend(username,friend);
        relationMapper.addFriend(friend, username);
        return "添加成功！";
    }

    public List<BaseUser> getFriendsByUsername(String username){
        List<String> list = relationMapper.getFriendsByUsername(username);
        List<BaseUser> friends = new ArrayList<>();
        for(String l:list){
            BaseUser bu = new BaseUser(l);
            User user = userService.selectByUsername(l);
            bu.setNickName(user.getNickname());
            friends.add(bu);
        }
        return friends;
    }
}
