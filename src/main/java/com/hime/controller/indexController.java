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
@RequestMapping("index")
public class indexController {

    @Autowired
    public DocScheduleService docScheduleService;
    @Autowired
    public DocInfoService docInfoService;

    @Autowired
    public DeptService deptService;


/**
 *
 * 前端展示
 * */
    @RequestMapping("")
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
