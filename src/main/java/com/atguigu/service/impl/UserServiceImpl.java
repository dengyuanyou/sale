package com.atguigu.service.impl;

import com.atguigu.domain.User;
import com.atguigu.mapper.UserMapper;
import com.atguigu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public User login(User user) {

        return userMapper.findUser(user);
    }
}
