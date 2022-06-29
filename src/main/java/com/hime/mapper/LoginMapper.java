package com.hime.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Mapper
@DS("mysql1")
@Repository("LoginMapper")
public interface LoginMapper extends BaseMapper<Object> {

    @Select("select count(1) from t_hos_userLogin where user = #{userName}")
    int checkUserName(String userName);

    @Select("select count(1) from t_hos_userLogin where user = #{userName} and pwd = #{pwd}")
    int checkPwd(String userName,String pwd);
}
