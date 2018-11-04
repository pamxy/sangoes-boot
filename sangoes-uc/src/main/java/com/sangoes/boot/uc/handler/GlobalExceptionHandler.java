package com.sangoes.boot.uc.handler;

import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.msg.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/30 10:26 PM
 */
@Slf4j
//@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获全部异常
     *
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result otherExceptionHandler(HttpServletResponse response, Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * 错误信息异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(HandleErrorException.class)
    @ResponseStatus(HttpStatus.SEE_OTHER)
    @ResponseBody
    public Result errorExceptionHandler(HttpServletResponse response,Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(ex.getMessage(), HttpStatus.SEE_OTHER);

    }
}
