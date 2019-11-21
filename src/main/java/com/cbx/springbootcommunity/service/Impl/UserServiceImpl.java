package com.cbx.springbootcommunity.service.Impl;

import com.cbx.springbootcommunity.domain.User;
import com.cbx.springbootcommunity.mapper.UserMapper;
import com.cbx.springbootcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void createOrUpdate(User user) {
        User dbUser=userMapper.findByAccountId(user.getAccountId());;
        if(dbUser==null){
                 //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
            //更新
        }
    }
}
