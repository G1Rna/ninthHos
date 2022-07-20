package com.hime.controller;

import com.alibaba.fastjson.JSONObject;
import com.hime.entity.*;
import com.hime.service.DeptService;
import com.hime.service.DocInfoService;
import com.hime.service.DocScheduleService;
import com.hime.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class indexController {

    @Autowired
    public DocScheduleService docScheduleService;
    @Autowired
    public DocInfoService docInfoService;
    @Autowired
    public LoginService loginService;
    @Autowired
    public DeptService deptService;
    /**
     *
     *      后台管理页面跳转方法
     * */
//    登录页面
    @RequestMapping("/Login")
    public String index(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", null);
        response.addCookie(cookie);
        return "index";
    }
//    登录成功（主页）
    @RequestMapping("/LoginSuccess")
    public String LoginSuccess(@CookieValue(value = "username")String userName) {
        if (userName == null || userName.equals("")){
            return "error";
        }
        return "userPage";
    }
//  导入医生信息
    @RequestMapping("/importDocInfoPage")
    public String importDocInfoPage(@CookieValue(value = "username")String userName) {
        if (userName == null || userName.equals("")){
            return "error";
        }
        return "importDocInfo";
    }
    //  导入医生照片
    @RequestMapping("/importDocPicPage")
    public String importDocPicPage(@CookieValue(value = "username")String userName, HttpServletRequest request) {
        if (userName == null || userName.equals("")){
            return "error";
        }
        List<String> systems = deptService.getAllSys();
        request.setAttribute("systems",systems);
        List<String> depts = deptService.getDeptBySys(systems.get(0));
        request.setAttribute("depts",depts);
        List<String> docNames = deptService.getDocNameByDpet(depts.get(0));
        request.setAttribute("docNames",docNames);
        return "importDocPic";
    }

    /**
     * 登录ajax
     * 并且设置cookie
     * */
    @RequestMapping("/LoginPerform")
    @ResponseBody
    public String LoginPerform(HttpServletResponse response, String userName, String pwd) {
        Cookie cookie = new Cookie("username", userName);
        //将cookie对象加入response响应
        response.addCookie(cookie);
        cookie.setMaxAge(60 * 60);
       return loginService.checkLogin(userName,pwd);
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
    public docInfo getDocInfo(String docName,String dept){
        docInfoService.getDocInfo(docName,dept);
        return null;
    }


/**
 *
 * 前端展示
 * */
    @RequestMapping("/getShowInfo")
    public String adminList(Model model, HttpSession request) {
        List<docInfo> docInfos = docScheduleService.getShowInfo();
        System.out.println(docInfos);

        return "index";
    }
    @RequestMapping("/getShowInfoForVue")
    @ResponseBody
    public List<docInfo> adminListForVue(Model model, HttpSession request) {
        List<docInfo> docInfos = docScheduleService.getShowInfo();

        return docInfos;
    }
    //转换为json字符串
    @RequestMapping("/getShowInfoForVueJsonString")
    @ResponseBody
    public String adminListForVueJsonString(Model model, HttpSession request) {
        List<docInfo> docInfos = docScheduleService.getShowInfo();

        String jsonString = JSONObject.toJSONString(docInfos);
        return jsonString;
    }

}
