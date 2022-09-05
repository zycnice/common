package com.zyc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyc.entity.SysUser;
import com.zyc.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 完成登录认证，UserDetailsService接口是spring security提供的
 * @author zyc
 * @version 1.0
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        SysUser sysUser = this.sysUserMapper.selectOne(queryWrapper);
        if (sysUser == null){
            return null;
        }
        //因为权限是多种，把权限放到集合中
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //获取该用户的权限
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysUser.getUserRole());
        //把权限放到集合中
        grantedAuthorities.add(grantedAuthority);
        //spring security提供的User方法，判断从前端传过来的密码是否一致
        return new User(username,sysUser.getPassword(),grantedAuthorities);
    }
}
