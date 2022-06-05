package com.retry_connection.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Component
public class HttpRequestInterceptor implements HandlerInterceptor {
    private byte[] ip;

    public byte[] getIp() {
        return ip;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        ip = request.getRemoteAddr().getBytes(StandardCharsets.UTF_8);
        return true;
    }
}
