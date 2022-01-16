package com.example.mybatis.demo.service;

import com.example.mybatis.demo.entity.Qun;
import com.example.mybatis.demo.entity.QunMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * TestBoot
 *
 * @author zhangchao01
 * @date 2021/11/11
 */
@Component
public class TestBoot implements CommandLineRunner {

    @Autowired
    private QunService qunService;
    @Autowired
    private CurrentTest currentTest;

    @Override
    public void run(String... args) throws Exception {
//        createQun();
//        createQunMember();
//        testNowVersion();
    }

    private void createQun(){
        Qun qun=new Qun();
        qun.setName("test");
        qun.setMemberCount(0);
        qun.setVersion(System.currentTimeMillis());
        qun.setUpdateTransaction("init");
        qunService.createQun(qun);
    }
    private void createQunMember(){
        QunMember memberA=new QunMember();
        memberA.setQunId(1);
        memberA.setUserId("A");
        memberA.setUpdateTransaction("init");
        qunService.createQunMember(memberA);

        QunMember memberB=new QunMember();
        memberB.setQunId(1);
        memberB.setUserId("B");
        memberB.setUpdateTransaction("init");
        qunService.createQunMember(memberB);

    }

    private void testNowVersion(){
        currentTest.testVersionBack4UpdateMember();
//          currentTest.testVersionBack4InsertMember();
          currentTest.testMemberCount();
//          currentTest.testDeadLock();
    }



}
