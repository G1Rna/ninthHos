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
    /*
    *
    *   hos_dept 先根据部门排序
    *   再根据部门中的职位排序
    * order by hos_dept,hos_doc_title='主任医师' desc ,hos_doc_title='副主任医师' desc
    *   20220609未加
    *
    *
    * */

    //根据日期格式改变，当前日期格式为20000101上午，需要多次拼接
    @Select("select * from v_hospital_schedule where \n" +
            "STR_TO_DATE(concat(concat(concat(concat(SUBSTRING(hos_time,1,4),'-'),SUBSTRING(hos_time,5,2)),'-'),SUBSTRING(hos_time,7,2)),'%Y-%m-%d') BETWEEN \n" +
            "(select date_format(now(),'%Y-%m-%d')) AND \n" +
            "date_format(DATE_ADD(now(),INTERVAL 7 day),'%Y-%m-%d')")
    /*---4
     * ---
     * ---
     * 改动需要与实体类一起改动
     * --
     * --
     * --*/
/*    //根据日期格式改变，当前日期格式为2000-01-01，无需多次拼接
    @Select("select * from v_hospital_schedule where \n" +
            "STR_TO_DATE(SUBSTRING(hos_time,1,10),'%Y-%m-%d') BETWEEN \n" +
            "(select date_format(now(),'%Y-%m-%d')) AND \n" +
            "date_format(DATE_ADD(now(),INTERVAL 7 day),'%Y-%m-%d')")*/
    List<docScheduleInfo> getInfo();

    @Select("select * from v_hospital_schedule where \n" +
            "STR_TO_DATE(concat(concat(concat(concat(SUBSTRING(hos_time,1,4),'-'),SUBSTRING(hos_time,5,2)),'-'),SUBSTRING(hos_time,7,2)),'%Y-%m-%d') BETWEEN \n" +
            "(select date_format(now(),'%Y-%m-%d')) AND \n" +
            "date_format(DATE_ADD(now(),INTERVAL 7 day),'%Y-%m-%d')")
    List<docInfo> getInfo1();
}
