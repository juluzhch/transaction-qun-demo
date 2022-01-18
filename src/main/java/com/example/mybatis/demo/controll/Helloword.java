package com.example.mybatis.demo.controll;

import com.example.mybatis.demo.service.client;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Helloword
 *
 * @author zhangchao01
 * @date 2021/11/10
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class Helloword {

    @GetMapping("/{userName}")
    public String hello(@PathVariable String userName) {
        return "hello" +userName;
    }

    @GetMapping("/zhongwen")
    public String zhongwen(@RequestHeader(name = "zhongwen") String account,HttpServletRequest request) throws UnsupportedEncodingException {
//        String code =URLDecoder.decode(account,"UTF-8");
        log.info("code=" +account);
        printRequestHead(request);
        return "收到中文" +request.getHeader("zhongwen");

    }

    @GetMapping("/testzhongwen")
    public String testzhongwen(HttpServletRequest request){
        client client = new client();
        client.testZhongwen();
        return "123";

    }


    @GetMapping("/portrait")
    public void innerException(HttpServletResponse response) throws IOException {
        response.sendRedirect(getPortrait());

    }
    private String getPortrait(){
        System.out.println("getPortrait");
        throw new NullPointerException();
    }

    /**
     * 输出请求头
     *
     * @param request 请求
     * @author zhangchao01
     * @date 2021/5/25 14:34
     */
    private void printRequestHead(HttpServletRequest request) {
        if (request == null) {
            return;
        }

        Enumeration<String> headers = request.getHeaderNames();
        List<String> headerList = new ArrayList<>();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            StringBuilder sb = new StringBuilder();
            while (headerValues.hasMoreElements()) {
                sb.append(headerValues.nextElement()).append(",");
            }
            headerList.add(headerName + ":" + sb.toString());
        }

        log.info("getPreviewUrl Header={}", headerList);
    }
}
