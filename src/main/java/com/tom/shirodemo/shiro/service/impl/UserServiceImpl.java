package com.tom.shirodemo.shiro.service.impl;

import com.tom.shirodemo.shiro.bean.User;
import com.tom.shirodemo.shiro.service.UserService;
import com.tom.shirodemo.shiro.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userservice")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper ;
    @Override
    public User getUserByName(String username) {
        return mapper.gerUserByName(username);
    }
}
