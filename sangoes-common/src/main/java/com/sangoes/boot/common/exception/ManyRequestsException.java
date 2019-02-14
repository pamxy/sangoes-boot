package com.sangoes.boot.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 请求过多异常
 *
 * @author jerrychir
 * @date 2019 2019/2/14 12:30 PM
 */
public class ManyRequestsException extends BaseException {

    private static final long serialVersionUID = 7303861659712996578L;

    /**
     *
     * @param message
     */
    public ManyRequestsException(String message) {
        super(message, HttpStatus.TOO_MANY_REQUESTS);
    }
}
