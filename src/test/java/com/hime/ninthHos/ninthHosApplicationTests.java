package com.hime.ninthHos;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hime.entity.docExcelImportInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ninthHosApplicationTests {

    @Test
    void contextLoads() {

    }

    public static void main(String[] args) {
        List<docExcelImportInfo> list = new ArrayList();
        EasyExcel.read("D:/date/收集数据格式.xls", docExcelImportInfo.class, new AnalysisEventListener<docExcelImportInfo>() {
                    //重写子类方法
                    @Override
                    public void invoke(docExcelImportInfo docExcelImportInfo1, AnalysisContext analysisContext) {
                        list.add(docExcelImportInfo1);
                    }
                    //重写子类方法
                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                    }
                }
        ).sheet().doRead();
        for (docExcelImportInfo docExcelImportInfo:list){
            System.out.println(docExcelImportInfo);
        }
        }
    }


