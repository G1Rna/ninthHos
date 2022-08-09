package com.hime.controller;

import com.hime.entity.docInfo;
import com.hime.service.DeptService;
import com.hime.service.DocInfoService;
import com.hime.service.DocScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("management")
public class managementController {
    @Autowired
    public DocScheduleService docScheduleService;
    @Autowired
    public DocInfoService docInfoService;

    @Autowired
    public DeptService deptService;
    //    登录成功（主页）
    @RequestMapping("")
    public String management() {
        return "userPage";
    }
    //  导入医生信息
    @RequestMapping("/importDocInfoPage")
    public String importDocInfoPage() {

        return "importDocInfo";
    }
    //  导入医生照片
    @RequestMapping("/importDocPicPage")
    public String importDocPicPage(HttpServletRequest request) {

        List<String> systems = deptService.getAllSys();
        request.setAttribute("systems",systems);
        List<String> depts = deptService.getDeptBySys(systems.get(0));
        request.setAttribute("depts",depts);
        List<String> docNames = deptService.getDocNameByDpet(depts.get(0));
        request.setAttribute("docNames",docNames);
        return "importDocPic";
    }


    /**
     * 根据EXCEL导入信息
     * */
    @RequestMapping("/importDocInfo")
    @ResponseBody
    public String importDocInfo(@RequestParam("files") MultipartFile files)throws Exception {
        docInfoService.importDocInfo(files);

        return "1";
    }

    /**
     * 上传照片
     * */
    //上传照片
    @RequestMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam("file")MultipartFile file,String docName,String dept){
        int flag = docInfoService.uploadImage(file,docName,dept);
        if (flag == 0){
            return "0";
        }
        return "1";
    }

    //根据系统查询部门list
    @RequestMapping("/getDeptBySys")
    @ResponseBody
    public List<String> getDeptBySys(String system){
        List<String> depts = deptService.getDeptBySys(system);
        return depts;
    }

    //根据部门查询医生姓名
    @RequestMapping("/getDocNameByDpet")
    @ResponseBody
    public List<String> getDocNameByDpet(String deptName){
        List<String> docNames = deptService.getDocNameByDpet(deptName);
        return docNames;
    }
    //根据名字和部门获取医生其他信息(暂时未用到，用于查询返回照片地址，展示照片)
    @RequestMapping("/getDocInfo")
    @ResponseBody
    public docInfo getDocInfo(String docName, String dept){
        docInfoService.getDocInfo(docName,dept);
        return null;
    }


}
