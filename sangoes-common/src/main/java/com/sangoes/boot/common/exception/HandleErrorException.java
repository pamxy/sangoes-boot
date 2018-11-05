package com.sangoes.boot.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Copyright (c) 2018
 * <p>
 * 操作失败(错误) 400 异常
 *
 * @author jerrychir
 * @date 2018/10/27 10:30 PM
 */
public class HandleErrorException extends BaseException {
    private static final long serialVersionUID = -7857206603577499485L;

    /**
     * Instantiates a new Base exception.
     *
     * @param message the message
     */
    public HandleErrorException(String message) {
        super(message, HttpStatus.SEE_OTHER);
    }
}
