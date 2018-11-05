package com.sangoes.boot.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Copyright (c) 2018 禁止访问 403 异常
 *
 * @author jerrychir
 * @date 2018/10/27 11:19 PM
 */
public class ForbiddenException extends BaseException {
    private static final long serialVersionUID = -984865412621251898L;

    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
