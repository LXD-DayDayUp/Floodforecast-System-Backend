package com.example.config.security.handler;

import com.alibaba.fastjson2.JSON;
import com.example.config.security.exception.CustomerAuthenticationException;
import com.example.utils.Result;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
//        设置客户端响应编码格式
        response.setContentType("application/json;charset=UTF-8");
//        获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
//        提示信息
        String message=null;
//        错误编码
        int code = 500;
//        判断异常类型
        if (exception instanceof AccountExpiredException) {
            message = "账户过期，登录失败";
        } else if (exception instanceof BadCredentialsException) {
            message = "用户名或密码错误，登录失败";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "密码过期，登录失败";
        } else if (exception instanceof DisabledException) {
            message = "账户被禁用，登录失败";
        } else if (exception instanceof LockedException) {
            message = "账户被锁，登录失败";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            message = "用户不存在，登录失败";
        } else if (exception instanceof CustomerAuthenticationException) {
            message=exception.getMessage();
            code=600;
        } else {
            message="登录失败";
        }
//        将错误信息转换为JSON
        String result = JSON.toJSONString(Result.error().code(code).message(message));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
