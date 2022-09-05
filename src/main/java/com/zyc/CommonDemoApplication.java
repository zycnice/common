package com.zyc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zyc.mapper")
public class CommonDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonDemoApplication.class, args);
    }

}
