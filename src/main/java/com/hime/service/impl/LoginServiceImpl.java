package com.hime.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hime.mapper.LoginMapper;
import com.hime.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper,Object> implements LoginService {
    @Autowired
    LoginMapper loginMapper;

    @Override
    public String checkLogin(String userName,String pwd){
        if (loginMapper.checkUserName(userName)==1){
            if (loginMapper.checkPwd(userName,pwd)==1){
                return "OK";
            }
            else {
                return "PWDERROR";
            }
        }
        else{
            return "USERNAMEERROR";
        }
    }



}
