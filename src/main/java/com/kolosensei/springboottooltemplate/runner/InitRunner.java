package com.kolosensei.springboottooltemplate.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2020/11/25 17:00
 * @description
 */
@Component
@Slf4j
@Order(1)
public class InitRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("-------------- project init --------------");
    }
}
