package com.example.demo.example.threadlocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/12/4 0004.
 */
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalControl {
    @RequestMapping("/test")
    @ResponseBody
    public Long test(){
        return ThreadLocalExample.getId();
    }
}
