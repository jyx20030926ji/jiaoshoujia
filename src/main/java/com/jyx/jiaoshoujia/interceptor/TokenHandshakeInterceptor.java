package com.jyx.jiaoshoujia.interceptor;


import com.jyx.jiaoshoujia.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class TokenHandshakeInterceptor implements HandshakeInterceptor {

    @Autowired
    private JwtUtils jwtUtils;



    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // 检查并解析令牌
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            String token = servletRequest.getParameter("token");

            if (token != null && !token.isEmpty()) {
                try {
                    // 根据你的业务逻辑解析令牌，获取用户 ID
                    Long userId = parseTokenAndGetUserId(token).longValue();
                    attributes.put("userId", userId); // 将用户 ID 存入 WebSocket 属性中
                } catch (Exception e) {
                    // 令牌解析失败
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return false;
                }
            } else {
                // 如果没有令牌，拒绝连接
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return false;
            }
        }

        return true; // 允许握手
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // 可选：握手完成后的逻辑
    }

    private Integer parseTokenAndGetUserId(String token) {

        Claims claims = jwtUtils.decoding(token);
        Object userIdObj = claims.get("userId");

       return  (Integer) userIdObj;

    }
}
