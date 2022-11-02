package com.example.config.security;

import com.example.config.security.filter.CheckTokenFilter;
import com.example.config.security.handler.AnonymousAuthenticationHandler;
import com.example.config.security.handler.CustomerAccessDeniedHandler;
import com.example.config.security.handler.LoginFailureHandler;
import com.example.config.security.handler.LoginSuccessHandler;
import com.example.config.security.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
@CrossOrigin
@Configuration
//开启security的使用
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;
    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;

    @Resource
    private CheckTokenFilter checkTokenFilter;
    //    注入加密处理类
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    配置认证处理器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());//密码加密
    }

    //   处理登陆认证
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录前进行过滤
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin()//表单登录
                .loginProcessingUrl("/api/user/login")//登录请求URL地址
                .successHandler(loginSuccessHandler)//认证成功处理器
                .failureHandler(loginFailureHandler)//认证失败处理器
                .and().csrf().disable()//禁用csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//不创建session
                .and().authorizeRequests()//设置需要拦截的请求
                .antMatchers("/api/user/login").permitAll()//登录请求放行
                .anyRequest().authenticated()//其他请求都需要拦截
                .and().exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationHandler)//匿名无权限访问
                .accessDeniedHandler(customerAccessDeniedHandler)//认证用户无权限访问
                .and().cors();//开启跨域配置
        System.out.println("结束");
    }
}
