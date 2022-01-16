package com.example.mybatis.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.mybatis.demo.mapper")
//@ComponentScan("com.study.filter")
//@ComponentScan("com.example.mybatis.demo.controll")
public class DemoApplication {

    public static void main(String[] args) {
//        codeTest();
        SpringApplication.run(DemoApplication.class, args);

    }


    public static void  codeTest(){
        List<String> accountList =new ArrayList<>();
        accountList.add("autotest648451");
        accountList.add("张德华");
        accountList.add("张autotest648得451@134化！@#￥￥……");
        accountList.add("#4dfsdfs");
        accountList.add("1sdsfs@455刘往里找……&*@qkkdf--=-2");
        accountList.add("fdsf-");
        accountList.add("tuisongx-in-xi01");
        try {
            doCode(accountList);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void doCode(List<String> userAccountList) throws UnsupportedEncodingException {

        String decode  = URLDecoder.decode("xxx-abcd-EDDD-1233-EDG","UTF-8");
        for(String account:userAccountList){
            //System.out.println("编码前：" +account );
            String code= URLEncoder.encode(account,"UTF-8");
            code=account;
            String code2=URLDecoder.decode(code,"UTF-8");
            if(!account.equals(code)||!account.equals(code2)){
                System.out.println("包括转码字符： " +account);
            }
            if(!account.equals(code2)){
                System.out.println("error " +account);
            }
        }
        System.out.println("ok");

    }

}
