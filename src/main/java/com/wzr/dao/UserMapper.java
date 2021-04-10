package com.wzr.dao;

import com.wzr.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper
{
    //登录验证
    User queryByName(String name);
    //角色验证
    String queryRoleByName(String name);
}
