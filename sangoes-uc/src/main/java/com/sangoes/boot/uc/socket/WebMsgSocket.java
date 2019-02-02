package com.sangoes.boot.uc.socket;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.AuthUtils;
import io.jsonwebtoken.Claims;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/1/31 10:30 AM
 */
@Slf4j
@ServerEndpoint(prefix = "sangoes.web.msg")
@Component
@Data
public class WebMsgSocket {

    /**
     * 当前线程的session 自己
     */
    private Session session;
    /**
     * webSocketMap
     */
    private static ConcurrentMap<String, CopyOnWriteArraySet<WebMsgSocket>> webSocketMap = new ConcurrentHashMap<String, CopyOnWriteArraySet<WebMsgSocket>>();
    /**
     * token
     */
    private String token;
    /**
     * user id
     */
    private String userId;

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, ParameterMap parameterMap) throws IOException {
        // 获取token
        String token = parameterMap.getParameter("token");
        //
        if (ObjectUtil.isNotNull(session) && ObjectUtil.isNotNull(token)) {
            // session
            this.session = session;
            // token
            this.token = token;
            // 获取claims
            Claims claims = AuthUtils.getClaims(token);
            String userId = claims.get("userId").toString();
            // user id
            this.userId = userId;
            // 保存在内存里
            CopyOnWriteArraySet<WebMsgSocket> webMsgSocketsDb = webSocketMap.get(userId);
            if (ObjectUtil.isNotNull(webMsgSocketsDb) && !webMsgSocketsDb.isEmpty()) {
                webMsgSocketsDb.add(this);
            } else {
                CopyOnWriteArraySet<WebMsgSocket> webMsgSockets = new CopyOnWriteArraySet<WebMsgSocket>();
                webMsgSockets.add(this);
                webSocketMap.put(userId, webMsgSockets);
            }
            // result
            Result<Object> result = Result.failed("连接socket消息主机成功");
            String json = JSONUtil.toJsonStr(result);
            session.sendText(json);
        } else {
            Result<Object> result = Result.failed("连接socket消息主机失败");
            String json = JSONUtil.toJsonStr(result);
            session.sendText(json);
        }

    }

    @OnClose
    public void onClose(Session session) throws IOException {
        CopyOnWriteArraySet<WebMsgSocket> webMsgSockets = webSocketMap.get(this.userId);
        if (ObjectUtil.isNotNull(webMsgSockets) && !webMsgSockets.isEmpty()) {
            webMsgSockets.remove(this);
            if (webMsgSockets.isEmpty()) {
                webSocketMap.remove(this.userId);
            }
        } else {
            log.debug("当前socket已关闭", session);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
//        log.info("userId:{}", AuthUtils.getToken());
        System.out.println(message);
        MsgPojo msgPojo = new MsgPojo();
        msgPojo.setId(61110000L);
        msgPojo.setName("jerry");
        String s = JSONUtil.toJsonStr(msgPojo);
        session.sendText(s);
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.session == null) ? 0 : this.session.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WebMsgSocket)) {
            return false;
        }
        WebMsgSocket other = (WebMsgSocket) obj;
        if (this.session == null) {
            if (other.session != null) {
                return false;
            }
        } else if (!this.session.equals(other.session)) {
            return false;
        }
        return true;
    }
}