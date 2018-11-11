package com.sangoes.boot.common.core.handler;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.exception.UnAuthoruzedException;
import com.sangoes.boot.common.msg.Result;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/30 10:26 PM
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获全部异常
     *
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result<String> otherExceptionHandler(HttpServletResponse response, Exception ex) {
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
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public Result<String> errorExceptionHandler(HttpServletResponse response, HandleErrorException ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);

    }

    /**
     * 验证错误信息异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public Result<String> vaildExceptionHandler(HttpServletResponse response, MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String errorMsg = allErrors.size() > 0 ? allErrors.get(0).getDefaultMessage() : "验证错误";
        return Result.failed(errorMsg, HttpStatus.UNPROCESSABLE_ENTITY);

    }

    /**
     * 未授权信息异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(UnAuthoruzedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result<String> authExceptionHandler(HttpServletResponse response, Exception ex) {
        log.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return Result.failed(ex.getMessage(), HttpStatus.UNAUTHORIZED);

    }
}
