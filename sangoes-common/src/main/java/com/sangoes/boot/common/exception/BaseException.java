package com.sangoes.boot.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/27 10:28 PM
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 3597724561702490397L;
    /**
     * 状态码
     */
    private HttpStatus status = HttpStatus.OK;

    /**
     * Gets status.
     *
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * Instantiates a new Base exception.
     */
    public BaseException() {
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message the message
     * @param status  the status
     */
    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message the message
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param cause the cause
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
