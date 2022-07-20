package com.hime.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hime.entity.docScheduleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@DS("mysql1")
@Repository("DocScheduleInfoMapper")
public interface DocScheduleInfoMapper extends BaseMapper<docScheduleInfo> {

    @Select("select * from t_doc_info where hos_doc_name = #{hosDocName} and hos_dept = #{hosDept}")
    docScheduleInfo getHosDocAllInfo(docScheduleInfo docScheduleInfo);
}
