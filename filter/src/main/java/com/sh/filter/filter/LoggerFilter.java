package com.sh.filter.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
//@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //진입 전
        log.info("진입전");

        //이미 getReader로 body를 읽어버려서 doFilter에서 다시 body를 읽을 수 없음
//        var req = new HttpServletRequestWrapper((HttpServletRequest) servletRequest);
//        var res = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
//
//        var br = req.getReader();
//        var list = br.lines().collect(Collectors.toList());
//        list.forEach(it->{
//            log.info("{}",it);
//        });

        //따로 내부에 contentCaching에 해당 내용을 담아두기에 뒤에서 다시 한 번 더 읽을 수 있음 (doFilter에서)
        var req = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);


        //둘의 결과 동일함
        //filterChain.doFilter(servletRequest, servletResponse);
        filterChain.doFilter(req, res);

        //이런 log들을 보고 body가 잘 담겨있는지 확인할 수 있음
        var reqjson = new String(req.getContentAsByteArray());
        var resjson = new String(res.getContentAsByteArray());

        log.info("req: {}", reqjson);
        log.info("res: {}", resjson);

        log.info("<<<<<리턴");

        //진입 후

        //getContentAsByte...는 캐싱된 내용이긴 하지만 new String으로 body를 읽어드려서 body는 빈 내용이므로
        //다시 body를 copy해주어야함
        res.copyBodyToResponse();

    }
}
