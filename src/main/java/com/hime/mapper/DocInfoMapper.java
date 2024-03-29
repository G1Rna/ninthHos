package com.hime.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hime.entity.docExcelImportInfo;
import com.hime.entity.docInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@DS("mysql1")
@Repository("DocInfoMapper")
public interface DocInfoMapper extends BaseMapper<docInfo> {
    @Select("select * from t_doc_info")
    List<docInfo> getInfo();

    @Select("select * from t_doc_info where hos_doc_name = #{hosDocName} and hos_dept = #{hosDept}")
    docInfo getHosDocAllInfo(docInfo docInfos1);

    @Insert("insert into t_doc_info set hos_doc_title=#{hosDocTitle},hos_doc_major=#{hosDocMajor},hos_doc_name=#{hosDocName},hos_dept=#{hosDept},hos_sys=#{hosSys}")
    int insertDocInfo(docExcelImportInfo docExcelImportInfo);

    @Delete("delete from t_doc_info where hos_doc_name=#{hosDocName} and hos_dept=#{hosDept}")
    int deleteDocInfo(docExcelImportInfo docExcelImportInfo);

    @Select("select hos_sys from t_sys_dept where hos_dept = #{dept}")
    String getDocSys(String dept);

    @Update("update t_doc_info set hos_doc_pic = #{imgPath} where hos_dept = #{dept} and hos_doc_name = #{docName}")
    int uploadImage(String imgPath,String docName,String dept);
}
