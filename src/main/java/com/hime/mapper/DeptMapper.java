package com.hime.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hime.entity.docInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@DS("mysql1")
@Repository("DeptMapper")
public interface DeptMapper extends BaseMapper<Object> {

    @Select("select hos_sys from t_doc_info group by hos_sys")
    List<String> getAllSys();

    @Select("select hos_dept from t_doc_info where hos_sys =  #{System} group by hos_dept")
    List<String> getDeptBySys(String System);

    @Select("select hos_doc_name from t_doc_info where hos_dept =  #{deptName}")
    List<String> getDocNameByDpet(String deptName);

}
