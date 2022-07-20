package com.hime.service.impl;

import com.hime.service.DocScheduleService;
import com.hime.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service//放到容器中
public class UserServiceImpl implements UserService {

    @DubboReference//新版本使用DubboReference// 引入POM坐标或者定义路径相同的接口名
    DocScheduleService docScheduleService;



    @Override
    public void buyTicket() {
        System.out.println("在注册中心拿到一张票："+docScheduleService.getScheduleInfo());
    }
}
