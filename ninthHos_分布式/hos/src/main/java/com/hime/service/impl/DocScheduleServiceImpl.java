package com.hime.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hime.entity.docScheduleInfo;
import com.hime.mapper.DocScheduleInfoMapper;
import com.hime.mapper.DocScheduleMapper;
import com.hime.service.DocScheduleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component//参考上方注释
@DubboService
public class DocScheduleServiceImpl extends ServiceImpl<DocScheduleMapper, docScheduleInfo> implements DocScheduleService {
    @Autowired
    DocScheduleMapper docScheduleMapper;
    @Autowired
    DocScheduleInfoMapper docScheduleInfoMapper;
    @Override
    public String getScheduleInfo() {
        List<docScheduleInfo> docScheduleInfos = docScheduleMapper.getInfo();
        for (docScheduleInfo docScheduleInfo :docScheduleInfos){
            docScheduleInfo docScheduleInfo1 = docScheduleInfoMapper.getHosDocAllInfo(docScheduleInfo);
            if(docScheduleInfo1 == null){
                docScheduleInfo.setHosDocTitle("暂无职称");
                docScheduleInfo.setHosDocDaily("暂无日期");
                docScheduleInfo.setHosDocMajor("暂无信息");
                docScheduleInfo.setHosSys("");
                docScheduleInfo.setHosDocPic("");
            }else{
                docScheduleInfo.setHosDocTitle(docScheduleInfo1.getHosDocTitle()==null?"暂无职称":docScheduleInfo1.getHosDocTitle());
                docScheduleInfo.setHosDocDaily(docScheduleInfo1.getHosDocDaily()==null?"暂无日期":docScheduleInfo1.getHosDocDaily());
                docScheduleInfo.setHosDocMajor(docScheduleInfo1.getHosDocMajor()==null?"暂无信息":docScheduleInfo1.getHosDocMajor());
                docScheduleInfo.setHosSys(docScheduleInfo1.getHosSys()==null?"":docScheduleInfo1.getHosSys());
                docScheduleInfo.setHosDocPic(docScheduleInfo1.getHosDocPic()==null?"":docScheduleInfo1.getHosDocPic());
            }



        }
        String jsonString = JSONObject.toJSONString(docScheduleInfos);
        return jsonString;
    }


}