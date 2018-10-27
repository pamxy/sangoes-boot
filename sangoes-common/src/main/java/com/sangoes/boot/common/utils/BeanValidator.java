package com.sangoes.boot.common.utils;

import com.sangoes.boot.common.exception.HandleErrorException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Copyright (c) 2018
 * 数据校验
 *
 * @author jerrychir
 * @date 2018/10/27 10:22 PM
 */
public class BeanValidator {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws HandleErrorException 校验不通过，则报HandleErrorException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws HandleErrorException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new HandleErrorException(msg.toString());
        }
    }

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new HandleErrorException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new HandleErrorException(message);
        }
    }
}