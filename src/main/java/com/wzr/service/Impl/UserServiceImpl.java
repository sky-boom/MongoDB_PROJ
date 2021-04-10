package com.wzr.service.Impl;


import com.wzr.dao.UserMapper;
import com.wzr.pojo.User;
import com.wzr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    //service层调用dao层
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryByName(String name)
    {
        return userMapper.queryByName(name);
    }

    @Override
    public String queryRoleByName(String name)
    {
        return userMapper.queryRoleByName(name);
    }
}
