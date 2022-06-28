package com.hime.controller;

import com.hime.entity.*;
import com.hime.service.DocInfoService;
import com.hime.service.DocScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class indexController {

    @Autowired
    public DocScheduleService docScheduleService;
    @Autowired
    public DocInfoService docInfoService;
    @RequestMapping("/getShowInfo")
    public String adminList(Model model, HttpSession request) {
        List<docInfo> docInfos = docScheduleService.getShowInfo();
        System.out.println(docInfos);

        return "index";
    }

    /**
     * 根据EXCEL导入信息
     * */
    @RequestMapping("/importDocInfo")
    public String importDocInfo(Model model, HttpSession request) {
        docInfoService.importDocInfo("");

        return "index";
    }

    /**
     * 上传照片
     * */
    @RequestMapping("/uploadImage")
    public String uploadImage(MultipartFile file,String docName,String dept){
        docInfoService.uploadImage(file,docName,dept);
        return "index";
    }

    /**
    * 根据名字和部门获取其他信息
    * */
    @RequestMapping("/getDocInfo")
    @ResponseBody
    public docInfo getDocInfo(String docName,String dept){
        docInfoService.getDocInfo(docName,dept);
        return null;
    }


}
