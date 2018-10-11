package com.fang.ychat.service;

import com.fang.ychat.dao.UserMapper;
import com.fang.ychat.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public boolean add(User user){
        userMapper.insert(user);
        return true;
    }
    public User selectByUsername(String username){
        return userMapper.selectByUsername(username);
    }
}
