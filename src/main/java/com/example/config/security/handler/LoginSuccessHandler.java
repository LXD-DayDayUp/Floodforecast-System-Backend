package com.example.config.security.handler;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.JSON;
import com.example.config.redis.RedisService;
import com.example.entity.User;
import com.example.utils.JwtUtils;
import com.example.utils.LoginResult;
import com.example.utils.ResultCode;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//    登陆认证成功处理器类
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisService redisService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        设置客户端响应的内容类型
        response.setContentType("application/json;charset=UTF-8");
//        获取当前登录用户信息
        User user = (User) authentication.getPrincipal();
//        生成token
        String token = jwtUtils.generateToken(user);
//        设置token签名密钥及过期时间
        long expireTime = Jwts.parser().setSigningKey(jwtUtils.getSecret())//设置密钥
                .parseClaimsJws(token.replace("jwt_","")).getBody().getExpiration().getTime();//设置过期时间
//        创建登陆结果对象
        LoginResult loginResult=new LoginResult(user.getId(), ResultCode.SUCCESS,token,expireTime);
////        消除循环引用
        String result = JSON.toJSONString(loginResult, SerializerFeature.DisableCircularReferenceDetect);
//        获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
//        把生成的token存到redis中
        String tokenKey="token_"+token;
        redisService.set(tokenKey,token,jwtUtils.getExpiration()/1000);
//        //设置客户端的响应的内容类型
//        response.setContentType("application/json;charset=UTF-8");
//        //获取当登录用户信息
//        User user = (User) authentication.getPrincipal();
//        //消除循环引用
//        String result = JSON.toJSONString(user, SerializerFeature.DisableCircularReferenceDetect);
//        //获取输出流
//        ServletOutputStream outputStream = response.getOutputStream();
//        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
//        outputStream.flush();
//        outputStream.close();
    }
}
