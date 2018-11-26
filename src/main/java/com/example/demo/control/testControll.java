package com.example.demo.control;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/11/18 0018.
 */
@Controller
@Slf4j
public class testControll {
    private Logger logger = LoggerFactory.getLogger(testControll.class);
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }
}
