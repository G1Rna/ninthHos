package com.hime.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

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



}
