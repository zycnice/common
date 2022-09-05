package com.zyc.controller;


import com.zyc.entity.SysWeather;
import com.zyc.mapper.SysUserMapper;
import com.zyc.service.SysWeatherService;
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
 *折线图
 * @author admin
 * @since 2022-09-04
 */
@Controller
@RequestMapping("/weather")
public class SysWeatherController {

    @Resource
    private SysWeatherService weatherService;

    @GetMapping("/line")
    @ResponseBody
    public Map<String, List> lineDate(){
        List<SysWeather> list = this.weatherService.list();
        List<String> dateList = new ArrayList<>();
        List<Integer> maxList = new ArrayList<>();
        List<Integer> minList = new ArrayList<>();

        for (SysWeather sysWeather : list) {
            dateList.add(sysWeather.getDate());
            maxList.add(sysWeather.getMaxTemperature());
            minList.add(sysWeather.getMinTemperature());
        }
        Map<String,List> map = new HashMap<>();
        map.put("date",dateList);
        map.put("max",maxList);
        map.put("min",minList);
        return map;
    }

}

