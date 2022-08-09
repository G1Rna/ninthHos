package com.hime.controller;

import com.hime.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class loginController {

    @Autowired
    public LoginService loginService;


    //    登录页面
    @RequestMapping("Login")
    public String Login(HttpServletResponse response,HttpServletRequest request) {
        Cookie cookie = new Cookie("userName", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "index";
    }

    /**
     * 登录ajax
     * 并且设置cookie
     * */
    @RequestMapping("Login/LoginPerform")
    @ResponseBody
    public String LoginPerform(HttpServletResponse response, String userName, String pwd) {
        String password = DigestUtils.md5DigestAsHex(pwd.getBytes());
        String code = loginService.checkLogin(userName,password);
        if (code.equals("OK")){
            Cookie cookie = new Cookie("userName", userName+"#"+password);
            //将cookie对象加入response响应
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
        }
        return code;
    }


}
