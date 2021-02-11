package com.matan.mongoredis.mongorediscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MongodbRedisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbRedisCacheApplication.class, args);
	}

}
