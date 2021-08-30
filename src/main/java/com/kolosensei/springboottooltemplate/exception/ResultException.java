package com.kolosensei.springboottooltemplate.exception;

import com.kolosensei.springboottooltemplate.handler.ResultStatus;
import lombok.Getter;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2020/11/25 19:40
 * @description 业务异常类
 */
@Getter
public class ResultException extends Exception {

    /**
     * 业务异常信息信息
     */
    ResultStatus resultStatus;

    public ResultException() {
        this(ResultStatus.INTERNAL_SERVER_ERROR);
    }

    public ResultException(ResultStatus resultStatus) {
        super(resultStatus.getMessage());
        this.resultStatus = resultStatus;
    }
}
