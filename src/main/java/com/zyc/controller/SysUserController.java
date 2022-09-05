package com.zyc.controller;


import com.zyc.entity.SysUser;
import com.zyc.service.SysUserService;
import com.zyc.util.MD5Util;
import com.zyc.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 这个包含了查询功能，key值可以为空
     * @param key
     * @param page 页码（第几页）
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "page",required = false) Integer page
    ){
        //没有传页码默认第1页
        if (page == null) { page = 1; }

        ModelAndView modelAndView = new ModelAndView();
        //先拿到数据源
        PageUtil pageUtil = this.sysUserService.queryUser(key, page);
        //获取用户数据源
        modelAndView.addObject("list",pageUtil.getData());
        modelAndView.addObject("page",page);
        //显示页的码
        List<Object> pages = new ArrayList<>();
        for (int i = 1; i <= pageUtil.getPages(); i++) {
            pages.add(i);
        }
        //总页数
        modelAndView.addObject("pages",pages);
        //
        modelAndView.addObject("pageCount",pageUtil.getPages());
        //总条数
        modelAndView.addObject("total",pageUtil.getTotal());
        //起始数据
        modelAndView.addObject("start",(page-1)*PageUtil.SIZE +1);
        //结尾数据
        Integer end = page*PageUtil.SIZE;
        if (end > pageUtil.getTotal()){
            modelAndView.addObject("end",pageUtil.getTotal());
        }else {
            modelAndView.addObject("end",end);
        }
        modelAndView.addObject("key",key);
        modelAndView.setViewName("user_list");
        return modelAndView;
    }

    /**
     * 新增用户
     * 密码加密
     * 伪删除默认为0
     * spring security提供的获取登录用户的方法，类似session
     * 创建日期和修改日期默认填充
     * @param sysUser
     * @return
     */
    @PostMapping("/save")
    public String save(SysUser sysUser){
        //密码加密
        sysUser.setPassword(MD5Util.getSaltMD5(sysUser.getPassword()));
        
        //spring security提供的获取登录用户的方法
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User admin = (User)authentication.getPrincipal();
        sysUser.setCreateUser(admin.getUsername());
        this.sysUserService.save(sysUser);
        return "redirect:/user/list";
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Integer id){
        boolean remove = this.sysUserService.removeById(id);
        if(remove){ return  "success";}
        return "fail";
    }

    /**
     * 根据id查找用户信息
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    @ResponseBody
    public Object findById(@PathVariable("id") Integer id){
        SysUser user = this.sysUserService.getById(id);
        return user;
    }

    @PostMapping("/updateUser")
    public String updateUserById(SysUser sysUser){
//        System.out.println("有执行到" + sysUser.getId() + sysUser.getUserName()+sysUser.getMobile());
        //密码加密
        sysUser.setPassword(MD5Util.getSaltMD5(sysUser.getPassword()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User admin = (User) authentication.getPrincipal();
        sysUser.setUpdateUser(admin.getUsername());
        this.sysUserService.updateById(sysUser);
        return "redirect:/user/list";
    }


}

