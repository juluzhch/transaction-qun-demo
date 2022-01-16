package com.example.mybatis.demo.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

/**
 * client
 *
 * @author zhangchao01
 * @date 2021/11/22
 */
public class client  {

    public void testHttp()  throws IOException{
        //1.打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.声明get请求
        HttpGet httpGet = new HttpGet("http://localhost:8080/hello/zhongwen");
//        httpGet.addHeader("zhongwen*","UTF-8'en-US'*1");
        String preCode = URLEncoder.encode("11","UTF-8");
        String code=new String("王敏".getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
//        System.out.println(code);
        code= URLEncoder.encode("张俊强","UTF-8");
        httpGet.addHeader("zhongwen",code);
        httpGet.addHeader("X-IM-Account",code);
        httpGet.addHeader("eng","xxx-abcd-EDDD-1233-EDG");//不编码只解码

        String decode  = URLDecoder.decode("xxx-abcd-EDDD-1233-EDG","UTF-8");
        System.out.println("decode:" +decode);

        String eng2= URLEncoder.encode("xxx-abcd-EDDD-1233-EDG","UTF-8");
        System.out.println("eng2:"+ eng2);
        httpGet.addHeader("eng2",eng2);//不解码只编码


        String eng3= URLEncoder.encode("xxx-abcd-EDDD-1233-EDG","UTF-8");
        System.out.println("eng3:"+ eng2);
        httpGet.addHeader("eng3",eng3);//编解码英文

//        httpGet.addHeader("abc","a; filename*=utf-8''encoded_text");
        //3.发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //4.判断状态码
        if(response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            //使用工具类EntityUtils，从响应中取出实体表示的内容并转换成字符串
            String string = EntityUtils.toString(entity, "utf-8");
            System.out.println(string);
        }
        //5.关闭资源
        response.close();
        httpClient.close();
    }

    public void testZhongwen() {
        try {
            testHttp();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
