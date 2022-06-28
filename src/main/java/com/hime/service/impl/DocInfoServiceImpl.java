package com.hime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hime.entity.docInfo;
import com.hime.mapper.DocInfoMapper;
import com.hime.service.DocInfoService;
import com.hime.util.ExcelUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class DocInfoServiceImpl extends ServiceImpl<DocInfoMapper,docInfo> implements DocInfoService {
    @Autowired
    DocInfoMapper docInfoMapper;


    @Override
    public List<docInfo> getInfo(){
        return docInfoMapper.getInfo();
    }

    @Override
    @Transactional
    public int importDocInfo(String filePath){
        filePath = "C:\\Users\\Administrator\\Desktop\\收集数据格式.xls";
        List<Map<String,String>> mapList = ExcelUtil.getDocInfoFromExcel(filePath);
        for(Map<String,String> map:mapList){
            docInfo docInfo = new docInfo();
            docInfo.setHosDocName(map.get("hosDocName"));
            docInfo.setHosDept(map.get("hosDept"));
            docInfo.setHosDocTitle(map.get("hosDocTitle"));
            docInfo.setHosDocMajor(map.get("hosDocMajor"));
            docInfo.setHosSys(docInfoMapper.getDocSys(map.get("hosDept")));

            docInfoMapper.deleteDocInfo(docInfo);
            docInfoMapper.insertDocInfo(docInfo);
        }
        return 1;
    }

    @Override
    public int uploadImage(MultipartFile file,String docName,String dept){
        if (file == null) {
            return 0;
//            return CommonResultVoUtil.error("请选择要上传的图片");
        }
        if (file.getSize() > 1024 * 1024 * 30) {
            return 0;
//            return CommonResultVoUtil.error("文件大小不能大于30M");
        }
        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return 0;
//            return CommonResultVoUtil.error("请选择jpg,jpeg,gif,png格式的图片");
        }
        String savePath = "D:/img/HeadImages/";
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            //将文件保存指定目录
            //file.transferTo(new File(savePath + filename));
            //File file1 = new File(file.getOriginalFilename());
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(savePath + filename));

            docInfoMapper.uploadImage(savePath + filename,docName,dept);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
//            return CommonResultVoUtil.error("保存文件异常");
        }
        //返回文件名称
        return 1;
    }


    public docInfo getDocInfo(String docName,String dept){
        LambdaQueryWrapper<docInfo> queryWrapper = Wrappers.<docInfo>lambdaQuery()
                .eq(docInfo::getHosDocName,docName)
                .eq(docInfo::getHosDept,dept);
        docInfo docInfo = docInfoMapper.selectOne(queryWrapper);
        return docInfo;
    }
}
