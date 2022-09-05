package com.zyc.service;

import com.zyc.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyc.util.PageUtil;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据key值查询用户信息
     * @param key
     * @return
     */
//    public List<SysUser> queryUser(String key);

    /**
     * 根据key值查询用户信息 ，还要返回分页信息
     * @param key
     * @param page 页码
     * @return
     */
    public PageUtil queryUser(String key,Integer page);

}
