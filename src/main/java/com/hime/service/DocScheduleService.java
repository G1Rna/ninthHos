package com.hime.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hime.entity.*;

import java.util.List;

public interface DocScheduleService extends IService<docScheduleInfo> {
    List<docScheduleInfo> getInfo();

    List<docInfo> getShowInfo();
}
