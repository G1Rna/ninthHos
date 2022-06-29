package com.hime.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hime.entity.docInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LoginService extends IService<Object> {

    String checkLogin(String userName,String pwd);


}
