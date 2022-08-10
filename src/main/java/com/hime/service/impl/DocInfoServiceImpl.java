package com.hime.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hime.entity.docExcelImportInfo;
import com.hime.entity.docInfo;
import com.hime.mapper.DocInfoMapper;
import com.hime.service.DocInfoService;
import com.hime.util.ExcelUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
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
    public int importDocInfo(@RequestParam("files") MultipartFile files)throws Exception{
        EasyExcel.read(files.getInputStream(), docExcelImportInfo.class, new AnalysisEventListener<docExcelImportInfo>() {
                    //重写子类方法
                    @Override
                    public void invoke(docExcelImportInfo docExcelImportInfo, AnalysisContext analysisContext) {
                        docExcelImportInfo.setHosSys(docInfoMapper.getDocSys(docExcelImportInfo.getHosDept()));
                        docInfoMapper.deleteDocInfo(docExcelImportInfo);
                        docInfoMapper.insertDocInfo(docExcelImportInfo);
                    }
                    //重写子类方法
                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                    }
                }
        ).sheet().doRead();
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
        String filename = dept+"_"+docName + "." + suffix;
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
