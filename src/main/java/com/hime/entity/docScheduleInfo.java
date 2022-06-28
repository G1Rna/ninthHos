package com.hime.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
@TableName
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class docScheduleInfo {
    private int id;
    private String hosTime;
    private String hosDept;
    private String hosDocName;
    private String hosDocStop;

    public void setHosTime(String hosTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        Calendar cal = Calendar.getInstance();
        //根据所给时间格式改变 当前为20000101上午
        try{
            date= sdf.parse(hosTime.substring(0,4)+"-"+hosTime.substring(4,6)+"-"+hosTime.substring(6,8));
            cal.setTime(date);
        }catch(Exception e){
            e.printStackTrace();
        }
        /*---
        * ---
        * ---
        * 改动需要与SQL一起改动
        * --
        * --
        * --*/
        /*//根据所给时间格式改变 当前为2000-01-01上午
        try{
            date= sdf.parse(hosTime.substring(0,10));
            cal.setTime(date);
        }catch(Exception e){
            e.printStackTrace();
        }*/
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        String weekDay = weekDays[w];

        this.hosTime = hosTime +"_"+weekDay+hosTime.substring(8,10);
//        this.hosTime = hosTime +"_"+weekDay+hosTime.substring(10,12);
    }
}
