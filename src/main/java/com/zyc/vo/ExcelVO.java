package com.zyc.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 用于接收excel文件上传
 * @author zyc
 * @version 1.0
 */
@Data
public class ExcelVO {
    /**
     * @ExcelProperty("商品编号")与excel表的列对应
     */
    @ExcelProperty("商品编号")
    private Integer id;
    @ExcelProperty("商品名称")
    private String name;
    @ExcelProperty("商品销量")
    private String sale;
}
