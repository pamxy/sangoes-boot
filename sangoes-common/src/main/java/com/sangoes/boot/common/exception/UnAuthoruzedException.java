/*
 * @Author: jerrychir @sangoes 
 * @Date: 2018-11-11 15:07:25 
 * @Last Modified by: jerrychir @sangoes
 * @Last Modified time: 2018-11-11 15:07:51
 */
package com.sangoes.boot.common.exception;

import org.springframework.http.HttpStatus;

/**
 * UnAuthoruzedException 没有授权异常
 */
public class UnAuthoruzedException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UnAuthoruzedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

}