package com.hime.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hime.entity.docInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DeptService extends IService<Object> {

    List<String> getAllSys();

    List<String> getDeptBySys(String system);

    List<String> getDocNameByDpet(String deptName);

}
