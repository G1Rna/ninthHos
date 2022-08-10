package com.hime.controller;

import com.alibaba.fastjson.JSONObject;
import com.hime.entity.*;
import com.hime.service.DeptService;
import com.hime.service.DocInfoService;
import com.hime.service.DocScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("index")
public class indexController {

    @Autowired
    public DocScheduleService docScheduleService;
    @Autowired
    public DocInfoService docInfoService;

    @Autowired
    public DeptService deptService;



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
