package com.hime;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication
//@MapperScan("com.hime.mapper")
@MapperScan("com.hime.mapper")
@SpringBootApplication
public class NinthHosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NinthHosApplication.class,args);

    }
}
