package com.hime.entity;


import java.util.List;

public class FileVo {
    String file_path1;
    List<String> file_name1;

    String file_path2;
    List<String> file_name2;

    public String getFile_path1() {
        return file_path1;
    }

    public void setFile_path1(String file_path1) {
        this.file_path1 = file_path1;
    }

    public List<String> getFile_name1() {
        return file_name1;
    }

    public void setFile_name1(List<String> file_name1) {
        this.file_name1 = file_name1;
    }

    public String getFile_path2() {
        return file_path2;
    }

    public void setFile_path2(String file_path2) {
        this.file_path2 = file_path2;
    }

    public List<String> getFile_name2() {
        return file_name2;
    }

    public void setFile_name2(List<String> file_name2) {
        this.file_name2 = file_name2;
    }

    @Override
    public String toString() {
        return "FileVo{" +
                "\n"+
                "file_path1='" + file_path1 + '\'' +
                ", file_name1=" + file_name1 +","+
                "\n"+
                " file_path2='" + file_path2 + '\'' +
                ", file_name2=" + file_name2 +
                "\n"+
                '}';
    }
}
