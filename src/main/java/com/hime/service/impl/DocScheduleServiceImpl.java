package com.hime.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hime.entity.*;
import com.hime.mapper.DocInfoMapper;
import com.hime.service.DocScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hime.mapper.DocScheduleMapper;

import java.util.List;

@Service
public class DocScheduleServiceImpl extends ServiceImpl<DocScheduleMapper, docScheduleInfo> implements DocScheduleService {
    @Autowired
    DocScheduleMapper docScheduleMapper;
    @Autowired
    DocInfoMapper docInfoMapper;
    @Override
    public List<docScheduleInfo> getInfo() {
        return docScheduleMapper.getInfo();
    }

    @Override
    public List<docInfo> getShowInfo() {
        List<docInfo> docInfos = docScheduleMapper.getInfo1();
        for (docInfo docInfos1 :docInfos){
            docInfo docInfo2 = docInfoMapper.getHosDocAllInfo(docInfos1);
            if(docInfo2 == null){
                docInfos1.setHosDocTitle("暂无职称");
                docInfos1.setHosDocDaily("暂无日期");
                docInfos1.setHosDocMajor("暂无信息");
                docInfos1.setHosSys("");
                docInfos1.setHosDocPic("");
            }else{
                docInfos1.setHosDocTitle(docInfo2.getHosDocTitle()==null?"暂无职称":docInfo2.getHosDocTitle());
                docInfos1.setHosDocDaily(docInfo2.getHosDocDaily()==null?"暂无日期":docInfo2.getHosDocDaily());
                docInfos1.setHosDocMajor(docInfo2.getHosDocMajor()==null?"暂无信息":docInfo2.getHosDocMajor());
                docInfos1.setHosSys(docInfo2.getHosSys()==null?"":docInfo2.getHosSys());
                docInfos1.setHosDocPic(docInfo2.getHosDocPic()==null?"":docInfo2.getHosDocPic());
            }
        }
            return docInfos;

    }
}
