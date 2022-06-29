package com.hime.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hime.entity.docInfo;
import com.hime.entity.docScheduleInfo;
import com.hime.mapper.DeptMapper;
import com.hime.mapper.DocInfoMapper;
import com.hime.mapper.DocScheduleMapper;
import com.hime.service.DeptService;
import com.hime.service.DocScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Object> implements DeptService {
    @Autowired
    DeptMapper deptMapper;

    @Override
    public List<String> getAllSys(){
        return deptMapper.getAllSys();
    }

    @Override
    public List<String> getDeptBySys(String System){
        return deptMapper.getDeptBySys(System);
    }
    @Override
    public List<String> getDocNameByDpet(String deptName){
        return deptMapper.getDocNameByDpet(deptName);
    }
}
