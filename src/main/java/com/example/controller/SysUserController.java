package com.example.controller;

import com.example.config.redis.RedisService;
import com.example.entity.Permission;
import com.example.entity.User;
import com.example.entity.UserInfo;
import com.example.utils.JwtUtils;
import com.example.utils.Result;
import com.example.vo.TokenVo;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {
    @Resource
    private RedisService redisService;
    @Resource
    private JwtUtils jwtUtils;

    /**
     * 刷新token
     */
    @PostMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String reToken = "";
        if (jwtUtils.validateToken(token, details)) {
            reToken = jwtUtils.refreshToken(token);
        }
        long expireTime = Jwts.parser().setSigningKey(jwtUtils.getSecret()).parseClaimsJws(reToken.replace("jwt_", "")).getBody().getExpiration().getTime();
        String oldTokenKey="token_"+token;
        redisService.del(oldTokenKey);
        String newTokenKey="token_"+reToken;
        redisService.set(newTokenKey,reToken, jwtUtils.getExpiration()/1000);
        TokenVo tokenVo=new TokenVo(expireTime,reToken);
        System.out.println("token刷新成功");
        return Result.ok(tokenVo).message("token刷新成功");

    }

    /*** 获取用户信息 ** @return */
    @GetMapping("/getInfo")
    public Result getInfo() {
        //从Spring Security上下文获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //判断authentication对象是否为空
        if (authentication == null) {
            return Result.error().message("用户信息查询失败");
        }
        //获取用户信息
        User user = (User) authentication.getPrincipal();
        //用户权限集合
        List<Permission> permissionList = user.getPermissionList();
        //获取角色权限编码字段
        Object[] roles = permissionList.stream() .filter(Objects::nonNull) .map(Permission::getCode).toArray();
        //创建用户信息对象
        UserInfo userInfo = new UserInfo(user.getId(),user.getNick_name(),null,null,roles);
        //返回数据
        return Result.ok(userInfo).message("用户信息查询成功"); }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        //获取token
        String token = request.getParameter("token");
        //如果没有从头部获取token，那么从参数里面获取
        if (ObjectUtils.isEmpty(token)) {
            token = request.getHeader("token");
        }
        //获取用户相关信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            //清空用户信息
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            //清空redis里面的token
            String key = "token_" + token; redisService.del(key);
            return Result.ok().message("用户退出成功");
        }
        return Result.ok().message("用户退出失败");
    }
}
