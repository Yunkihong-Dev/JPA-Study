package com.app.jwtsecurity.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class MyFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("필터2");
        chain.doFilter(request,response);
    }
}
