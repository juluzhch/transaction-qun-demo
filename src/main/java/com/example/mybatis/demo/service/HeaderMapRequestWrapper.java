package com.example.mybatis.demo.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;
/**
 * HeaderMapRequestWrapper
 *
 * @author zhangchao01
 * @date 2021/11/22
 */
public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
    private Map<String, String> headerMap = new HashMap<String, String>();
    public HeaderMapRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }
    @Override
    public String getHeader(String name) {
        String headerValue = super.getHeader(name);
        if (headerMap.containsKey(name)) {
            headerValue = headerMap.get(name);
        }
        return headerValue;
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(headerMap.keySet());
        return Collections.enumeration(names);
    }
    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values ;
        if (headerMap.containsKey(name)) {
//            values.clear();
//            values.add(headerMap.get(name));
            values =Collections.singletonList(headerMap.get(name));
        }else{
            values = Collections.list(super.getHeaders(name));
        }
        return Collections.enumeration(values);
    }
}
