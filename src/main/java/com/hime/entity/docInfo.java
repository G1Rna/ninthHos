package com.hime.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@TableName
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class docInfo {
    private int id;
    private String hosTime;
    private String hosSys;
    private String hosDept;
    private String hosDocName;
    private String hosDocTitle;
    private String hosDocMajor;
    private String hosDocDaily;
    private String hosDocStop;
    private String hosDocPic;


    public String getHosDocPic() {

        if(hosDocPic==null||hosDocPic.equals("")){
            return "D:/img/HeadImages/暂无照片.png";
        }

        return hosDocPic;
    }

    public void setHosDocPic(String hosDocPic) {
        this.hosDocPic = hosDocPic;
    }
}
