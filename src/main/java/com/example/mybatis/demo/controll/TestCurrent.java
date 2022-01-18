package com.example.mybatis.demo.controll;

import com.example.mybatis.demo.service.CurrentTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestCurrent
 *
 * @author zhangchao01
 * @date 2021/11/12
 */
@RestController
@Slf4j
@RequestMapping("/test/current/")
public class TestCurrent {

    @Autowired
    private CurrentTest currentTest;

    @GetMapping("/member-count")
    public void memberCount() {
        currentTest.testMemberCount();
    }

    @GetMapping("/version-back-insert")
    public void versionBackInsertMember() {
        currentTest.testVersionBack4InsertMember();
    }

    @GetMapping("/version-back-updatemember")
    public void versionBack() {
        currentTest.testVersionBack4UpdateMember();
    }

    @GetMapping("/deadlock")
    public void deadLock() {
        try{
            currentTest.testDeadLock();
        }catch (Exception e){
            log.error("deadLock-死锁",e);
        }
    }

    @GetMapping("/dbversion")
    public void dbVersion(){
        currentTest.testDBVersionBack4InsertMember();
    }
}
