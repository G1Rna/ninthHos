package com.hime.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hime.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@DS("mysql2")
@Repository("DocScheduleMapper")
public interface DocScheduleMapper extends BaseMapper<docScheduleInfo> {

    //根据日期格式改变，当前日期格式为2000-01-01，无需多次拼接
    @Select("select * from v_hospital_schedule where \n" +
            "STR_TO_DATE(SUBSTRING(hos_time,1,10),'%Y-%m-%d') BETWEEN \n" +
            "(select date_format(now(),'%Y-%m-%d')) AND \n" +
            "date_format(DATE_ADD(now(),INTERVAL 7 day),'%Y-%m-%d')")
    List<docScheduleInfo> getInfo();

    @Select("select * from v_hospital_schedule where\n" +
            "STR_TO_DATE(SUBSTRING(hos_time,1,10),'%Y-%m-%d') BETWEEN \n" +
            "(select date_format(now(),'%Y-%m-%d')) AND\n" +
            "date_format(DATE_ADD(now(),INTERVAL 7 day),'%Y-%m-%d')")
    List<docInfo> getInfo1();
}
