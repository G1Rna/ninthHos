package com.hime.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hime.entity.docInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@DS("mysql2")
@Repository("DocInfoMapper")
public interface DocInfoMapper extends BaseMapper<docInfo> {
    @Select("select * from t_doc_info")
    List<docInfo> getInfo();

    @Select("select * from t_doc_info where hos_doc_name = #{hosDocName} and hos_dept = #{hosDept}")
    docInfo getHosDocAllInfo(docInfo docInfos1);

    @Insert("insert into t_doc_info set hos_doc_title=#{hosDocTitle},hos_doc_major=#{hosDocMajor},hos_doc_name=#{hosDocName},hos_dept=#{hosDept},hos_sys=#{hosSys}")
    int insertDocInfo(docInfo docInfos1);

    @Delete("delete from t_doc_info where hos_doc_name=#{hosDocName} and hos_dept=#{hosDept}")
    int deleteDocInfo(docInfo docInfos1);

    @Select("select hos_sys from t_sys_dept where hos_dept = #{dept}")
    String getDocSys(String dept);

    @Update("update hos_sys set hos_doc_pic = #{imgPath} where hos_dept = #{dept} and hos_doc_name = #{docName}")
    int uploadImage(String imgPath,String docName,String dept);
}
