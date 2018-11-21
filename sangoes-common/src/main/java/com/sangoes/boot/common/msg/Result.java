package com.sangoes.boot.common.msg;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Copyright (c) 2018 api统一返回结果
 *
 * @author jerrychir
 * @date 2018/10/30 10:47 PM
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -7136275879390165308L;
    /**
     * 状态码
     */
    private int code;
    /**
     * 返回值
     */
    private T data;
    /**
     * 返回信息
     */
    private String msg;

    public Result() {
    }

    private Result(T data, String msg, int code) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 没有返回信息
     *
     * @param msg
     * @param code
     * @param response
     * @throws Exception
     */
    public static void noReturn(String msg, HttpStatus code, HttpServletResponse response) throws IOException {
        // 设置code
//        response.setStatus(code.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        // 设置返回result
        Result<Object> result = new Result<>();
        result.setCode(code.value());
        result.setMsg(msg);
        // 回写数据
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSONUtil.toJsonStr(result));
    }

    /**
     * 成功没有返回信息
     *
     * @param msg
     * @param data
     * @param response
     * @throws Exception
     */
    public static void success(Object data, String msg, HttpServletResponse response) throws IOException {
        // 设置code
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        // 设置返回result
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setData(data);
        result.setMsg(msg);
        // 回写数据
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSONUtil.toJsonStr(result));
    }

    /**
     * 成功返回
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return new Result<>(null, "成功", HttpStatus.OK.value());
    }

    /**
     * 成功返回
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(String msg) {
        return new Result<>(null, msg, HttpStatus.OK.value());
    }

    /**
     * 成功返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(data, msg, HttpStatus.OK.value());
    }

    /**
     * 失败返回
     *
     * @param msg
     * @param <T>
     * @return
     */
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public static <T> Result<T> failed(String msg, HttpStatus code) {
        return new Result<>(null, msg, code.value());
    }

    /**
     * 失败返回
     *
     * @param msg
     * @param <T>
     * @return
     */
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public static <T> Result<T> failed(String msg) {
        return new Result<>(null, msg, HttpStatus.EXPECTATION_FAILED.value());
    }

    /**
     * 其他返回
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> restResult(String msg, HttpStatus code) {
        return new Result<>(null, msg, code.value());
    }

    /**
     * 其他返回
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> restResult(T data, String msg, HttpStatus code) {
        return new Result<>(data, msg, code.value());
    }
}
