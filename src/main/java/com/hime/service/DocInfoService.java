package com.hime.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hime.entity.docInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocInfoService extends IService<docInfo> {

    List<docInfo> getInfo();

    int importDocInfo(String filePath);

    int uploadImage(MultipartFile file,String docName,String dept);

    docInfo getDocInfo(String docName,String dept);
}
