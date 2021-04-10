package com.wzr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.wzr.dao")
public class MongoApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(MongoApplication.class, args);
    }

}
