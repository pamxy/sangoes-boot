package com.sangoes.boot.uc.security;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.sangoes.boot.common.exception.UnAuthoruzedException;
import com.sangoes.boot.uc.config.IgnoreUrlsConfig;
import com.sangoes.boot.uc.constants.SecurityConstants;
import com.sangoes.boot.uc.modules.admin.service.impl.UserDetailsServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/28 11:13 AM
 */
@Component
public class JwtTokenProvider {
    /**
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key
     * here. Ideally, in a microservices environment, this key would be kept on a
     * config-server.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000;
    // 忽略url
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    private UserDetailsServiceImpl myUserDetails;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username) {

        Claims claims = Jwts.claims().setSubject(username);
        // claims.put("auth", roles.stream().map(s -> new
        // SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        // 获取uri
        String uri = req.getRequestURI();
        // FIXME 临时解决图片验证码
        if (uri.startsWith("/api/captcha/image/")) {
            return null;
        }
        // 获取 token
        String token = req.getHeader(SecurityConstants.SECURITY_AUTHORIZATION);
        // basic token
        if (!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.SECURITY_BASIC)
                && StrUtil.equals(secretKey, token.substring(6, token.length()))) {
            // 放行
            return null;
        }
        // beaerer token
        if (!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.SECURITY_BEARER)) {
            return token.substring(7, token.length());
        }
        throw new UnAuthoruzedException("授权不存在");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new UnAuthoruzedException("授权过期");
        }
    }
}
