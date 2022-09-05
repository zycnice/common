package com.zyc.configuration;

import com.zyc.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * spring security权限认证框架
 * @author zyc
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailServiceImpl userDetailService;

    /**
     * 登录验证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //调用userDetailsService方法，把impl写的实现填入即可
        auth.userDetailsService(this.userDetailService).passwordEncoder(new MyPasswordEncoder());
    }

    /**
     * 权限认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //追加权限
        http.authorizeRequests()
                //antMatchers("/**")对所有请求进行验证;access("hasRole('ADMIN')")要求都要具备ADMIN的角色
                .antMatchers("/**").access("hasRole('ADMIN')")
                .and()
                .formLogin()
                //访问任何一个页面，要是没有登录，直接跳转到登录页面
                .loginPage("/login")
                //对于登录页面要放行
                .permitAll()
                .and()
                .logout()
                //退出也要放行
                .permitAll()
                .and()
                //关掉
                .csrf()
                .disable();

        //登录账号和密码错抛出异常，追加一个处理器，使用匿名类内类去实现
        http.exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        httpServletRequest.setAttribute("authError", true);
                        httpServletRequest.getRequestDispatcher(httpServletRequest.getContextPath()+"/login").forward(httpServletRequest, httpServletResponse);
                    }
                });
        //解决iframe不能使用问题,允许使用iframe，使子页面能在iframe显示
        http.headers().frameOptions().sameOrigin();
    }
}
