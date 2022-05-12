package com.hiddencat.picsmgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: baiyaoyu
 * @Date: 2022/5/6
 * @Description:
 */
@SpringBootApplication
public class PicsmgtApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PicsmgtApplication.class, args);
    }
}
