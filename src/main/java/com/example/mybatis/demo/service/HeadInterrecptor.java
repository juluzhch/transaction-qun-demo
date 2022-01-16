//package com.example.mybatis.demo.service;
//
//import java.net.URLDecoder;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
///**
// * HeadInterrecptor
// *
// * @author zhangchao01
// * @date 2021/11/22
// */
//@Component
//@Slf4j
//public class HeadInterrecptor  implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
////        HttpServletResponse response,
////        Object handler) throws Exception {
////
////        String code = request.getHeader("zhongwen");
////        code = URLDecoder.decode(code,"UTF-8");
////        request.
//        //判断用户是否登录，未登录重定向到登录页面
////        if (request.getSession().getAttribute("user") == null){
////            response.sendRedirect("/admin");
////            return false;
////        }
//        return true;
//    }
//
//}
