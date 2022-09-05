package com.zyc.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zyc.vo.ExcelVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 用于上传excel文件
 * @Controller会返回视图，文件上传不需要视图
 * 所以改成@RestController或者在方法上加@ResponseBody
 * @author zyc
 * @version 1.0
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("/importExcel")
    public void importExcel(@RequestParam("file")MultipartFile file){
        //把文件读入
        try {
            EasyExcel.read(file.getInputStream())
                    //把自己设计好的模板传入
                    .head(ExcelVO.class)
                    .sheet()
                    //注册一个监听器，每读一行都监听,，然后封装
                    .registerReadListener(new AnalysisEventListener<ExcelVO>() {

                        @Override
                        public void invoke(ExcelVO excelVO, AnalysisContext analysisContext) {
                            //把一行解析为对象
                            System.out.println(excelVO);
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                            System.out.println("=======文件解析完成======");
                        }
                        //.doRead();监听器执行
                    }).doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
