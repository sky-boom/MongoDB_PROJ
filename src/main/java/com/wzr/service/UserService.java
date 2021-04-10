package com.wzr.service;

import com.wzr.pojo.User;

public interface UserService
{
    //登录验证
    User queryByName(String name);
    //角色验证
    String queryRoleByName(String name);
}
