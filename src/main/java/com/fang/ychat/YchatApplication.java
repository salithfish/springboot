package com.fang.ychat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fang.ychat.dao")
public class YchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(YchatApplication.class, args);
	}
}
