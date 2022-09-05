package com.zyc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyc.entity.SysUser;
import com.zyc.mapper.SysUserMapper;
import com.zyc.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyc.util.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author admin
 * @since 2022-09-02
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public PageUtil queryUser(String key,Integer page) {
        //设置分页页面及其每页显示的行数
        Page<SysUser> userPage = new Page<>(page, PageUtil.SIZE);

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        //模糊查询,当用户名和昵称不为空时查询
        queryWrapper.like(StringUtils.isNotBlank(key), "user_name", key)
                .or().like(StringUtils.isNotBlank(key), "nick_name", key);

        //查询分页情况和数据
        Page<SysUser> resultPage = this.sysUserMapper.selectPage(userPage, queryWrapper);
        //把数据封装到工具类中
        PageUtil pageUtil = new PageUtil();
        pageUtil.setData(resultPage.getRecords());
        pageUtil.setPage(page);
        pageUtil.setTotal(resultPage.getTotal());
        pageUtil.setPages(resultPage.getPages());
        return pageUtil;
    }

//    @Override
//    public List<SysUser> queryUser(String key) {
//        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
//        //模糊查询,当用户名和昵称不为空时查询
//        queryWrapper.like(StringUtils.isNotBlank(key),"user_name",key)
//                .or().like(StringUtils.isNotBlank(key),"nick_name",key);
//        return this.sysUserMapper.selectList(queryWrapper);
//    }
}
