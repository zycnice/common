package com.zyc.controller;


import com.zyc.entity.SysProduct;
import com.zyc.service.SysProductService;
import com.zyc.vo.SysProductVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *饼状图
 * @author admin
 * @since 2022-09-04
 */
@Controller
@RequestMapping("/product")
public class SysProductController {

    @Resource
    private SysProductService productService;

    @GetMapping("/pie")
    @ResponseBody
    public Map<String,List> pie(){
        List<SysProduct> list = this.productService.list();
        SysProductVO vo;
        List<SysProductVO> voList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        for (SysProduct sysProduct : list) {
            vo = new SysProductVO();
            vo.setName(sysProduct.getName());
            vo.setValue(sysProduct.getSale());
            voList.add(vo);
            nameList.add(sysProduct.getName());
        }
        Map<String,List>  map= new HashMap<>();
        map.put("title",nameList);
        map.put("data",voList);
        return map;
    }
}

