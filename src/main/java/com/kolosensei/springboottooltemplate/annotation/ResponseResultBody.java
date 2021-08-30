package com.kolosensei.springboottooltemplate.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2020/11/25 19:33
 * @description 自定义注解，全局json格式处理
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseResultBody {
}
