package com.wzr.pojo;

public class User
{
    private int user_id;        //用户id
    private String identity;    //用户身份
    private String username;    //用户名
    private String password;    //密码

    public User() { }

    public User(int user_id, String identity, String username, String password)
    {
        this.user_id = user_id;
        this.identity = identity;
        this.username = username;
        this.password = password;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public String getIdentity()
    {
        return identity;
    }

    public void setIdentity(String identity)
    {
        this.identity = identity;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "user_id=" + user_id +
                ", identity='" + identity + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
