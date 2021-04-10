package com.wzr.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController
{
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/register")
    public String register()
    {
        return "register";
    }

    @RequestMapping("/tologin")
    public String tologin(String username, String password, Model model)
    {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //这个方法会把用户信息提交到 doGetAuthtication【认证】方法中校验用户信息。
        try
        {
            subject.login(token);   //执行登录的方法，如果没有异常，就成功
            return "main";  //登录成功，进入首页
        }
        catch (UnknownAccountException e)   //账户不存在异常
        {
            model.addAttribute("msg", "账户不存在!");
            return "login";
        }
        catch (IncorrectCredentialsException e) //密码错误
        {
            model.addAttribute("msg", "密码错误!");
            return "login";
        }
    }

    //注销
    @RequestMapping("/logout")
    public String logout()
    {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "login";
    }

    //测试
    @RequestMapping("/main")
    public String test()
    {
        return "main";
    }

    @RequestMapping("/tomain")
    public String tomain()
    {
        return "redirect:main";
    }
}
