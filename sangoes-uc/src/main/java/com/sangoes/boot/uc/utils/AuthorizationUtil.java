package com.sangoes.boot.uc.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import com.sangoes.boot.uc.constants.SecurityConstants;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Copyright (c) 2018
 * 认证授权相关工具类
 *
 * @author jerrychir
 * @date 2018/11/20 9:53 PM
 */
public class AuthorizationUtil {
    /**
     * 从header 请求中的clientId/secect
     *
     * @param header
     * @return
     * @throws IOException
     */
    public static String[] extractAndDecodeHeader(String header)
            throws IOException {
        // 获取base64Token
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        // Base64解密
        byte[] decoded = Base64.decode(base64Token);
        // 获取token
        String token = new String(decoded, StandardCharsets.UTF_8);
        // 分割token
        int delim = token.indexOf(":");
        // 判读delim
        if (delim == -1) {
            throw new UnapprovedClientAuthenticationException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }

    /**
     * 从header 请求中的clientId/secect
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String[] extractAndDecodeHeader(HttpServletRequest request)
            throws IOException {
        // 获取header
        String header = request.getHeader(SecurityConstants.SECURITY_AUTHORIZATION);
        // 判断header
        if (StrUtil.isBlank(header) || !header.startsWith(SecurityConstants.SECURITY_BASIC)) {
            throw new UnapprovedClientAuthenticationException("请求头中client信息为空");
        }
        return extractAndDecodeHeader(header);
    }
}
