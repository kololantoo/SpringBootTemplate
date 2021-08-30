package com.kolosensei.springboottooltemplate.handler;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2020/11/25 19:24
 * @description http状态码枚举类
 */
@ToString
@Getter
public enum ResultStatus {

    SUCCESS(HttpStatus.OK, 200, "OK", true),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "Bad Request", false),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Internal Server Error", false),
    ;

    /**
     * 返回的HTTP状态码,  符合http请求
     */
    private HttpStatus httpStatus;
    /**
     * 业务异常码
     */
    private Integer code;
    /**
     * 业务异常信息描述
     */
    private String message;
    /**
     * 请求状态
     */
    private Boolean status;

    ResultStatus(HttpStatus httpStatus, Integer code, String message, Boolean status) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
