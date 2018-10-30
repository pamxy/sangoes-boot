package com.sangoes.boot.common.handler;

import com.sangoes.boot.common.msg.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/30 10:26 PM
 */
@Slf4j
@ControllerAdvice({"com.sangoes.boot"})
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 捕获全部异常
     *
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result otherExceptionHandler(HttpServletResponse response, Exception ex) {
        log.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return Result.failed(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }
}
