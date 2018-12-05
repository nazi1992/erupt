package com.example.demo;


import com.example.demo.example.threadlocal.ThreadLocalExample;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2018/12/4 0004.
 */
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println("do fileter"+Thread.currentThread().getId()+"=value="+httpServletRequest.getServletPath());
        ThreadLocalExample.set(Thread.currentThread().getId());//在过滤器中方数据
        filterChain.doFilter(servletRequest,servletResponse);//如果只是做一些处理而不是拦截，需要加上这句
    }

    @Override
    public void destroy() {

    }
}
