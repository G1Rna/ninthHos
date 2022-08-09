package com.hime.interceptor;

import com.hime.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CookieInterceptor implements HandlerInterceptor {
    @Autowired
    public LoginService loginService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userName = "";
        String pwd = "";
        //如果返回false则 postHandle()和 afterCompletion()不会执行
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length>0){
            for(Cookie cookie:cookies) {
                if (StringUtils.equalsIgnoreCase(cookie.getName(), "userName")) {
                    userName = cookie.getValue().split("#")[0];
                    pwd = cookie.getValue().split("#")[1];
                }
            }
            String code = loginService.checkLogin(userName,pwd);
            if (code.equals("OK")){
                return true;
            }
            }
        log.info("拦截，返回Login页面");
        response.sendRedirect("/Login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
