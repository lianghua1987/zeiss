package com.hua.interview.zeiss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZeissApplication {
    private static final Logger logger = LoggerFactory.getLogger(ZeissApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ZeissApplication.class, args);
        logger.info("============= Starting Zeiss interview api =============");
        logger.info("============= Api Endpoint: localhost:8080/api =============");
        logger.info("============= This application uses in memory storage for websocket data for simplicity =============");
        logger.info("============= Stopping instance will cause data loss =============");
        logger.info("============= To use permanent storage, change to binary or text file storing on local disk =============");
    }
}