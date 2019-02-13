package com.sangoes.boot.common.core.handler;

import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.exception.UnAuthoruzedException;
import com.sangoes.boot.common.msg.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Copyright (c) 2018
 * extends ResponseEntityExceptionHandler
 * 通用异常捕获
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
    @ExceptionHandler({Exception.class})
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
    @ResponseBody
    public Result<String> authExceptionHandler(HttpServletResponse response, Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(ex.getMessage(), HttpStatus.UNAUTHORIZED);

    }

//    /**
//     * 通用的接口映射异常处理方
//     */
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
//                                                             HttpStatus status, WebRequest request) {
//        if (ex instanceof MethodArgumentNotValidException) {
//            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
//            return new ResponseEntity<>(new ErrorResponseEntity(status.value(), exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()), status);
//        }
//        if (ex instanceof MethodArgumentTypeMismatchException) {
//            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
//            logger.error("参数转换失败，方法：" + exception.getParameter().getMethod().getName() + "，参数：" + exception.getName()
//                    + ",信息：" + exception.getLocalizedMessage());
//            return new ResponseEntity<>(new ErrorResponseEntity(status.value(), "参数转换失败"), status);
//        }
//        return new ResponseEntity<>(new ErrorResponseEntity(status.value(), "参数转换失败"), status);
//    }
}
