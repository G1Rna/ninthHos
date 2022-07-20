package com.hime.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hime.entity.docScheduleInfo;


public interface DocScheduleService extends IService<docScheduleInfo> {
    String getScheduleInfo();

}
