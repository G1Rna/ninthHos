package com.hime.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class docExcelImportInfo {
    @ExcelProperty(value = "医生姓名")
    private String hosDocName;

    @ExcelProperty(value = "所在部门")
    private String hosDept;

    @ExcelProperty(value = "职位")
    private String hosDocTitle;

    @ExcelProperty(value = "主治")
    private String hosDocMajor;

    private String hosSys;

}
