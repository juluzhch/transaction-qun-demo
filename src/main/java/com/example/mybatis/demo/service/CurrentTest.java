package com.example.mybatis.demo.service;

import com.example.mybatis.demo.entity.QunMember;
import com.example.mybatis.demo.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

//测试当前方案
@Service
@Slf4j
public class CurrentTest {
    @Autowired
    private QunService qunService;

    private long qunId=1;
    private long memberA=1;
    private long memberB=2;

    //验证当前方案version会回退
    public void testVersionBack4UpdateMember(){

        //两个线程分别执行
        CountDownLatch downLatch=new CountDownLatch(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        log.info("testVersionBack4UpdateMember 开始执行...");
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                updateMemberAndQun(qunId,memberA,500,0,downLatch,cyclicBarrier);
            }
        });
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
               //sleepBefore =10 ,保证比A晚开始执行
                updateMemberAndQun(qunId,memberB,0,100,downLatch,cyclicBarrier);
            }
        });
        threadA.start();
        threadB.start();
        try {
            downLatch.await();
            log.info("testVersionBack4UpdateMember 执行完毕！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testVersionBack4InsertMember(){

        //两个线程分别执行
        CountDownLatch downLatch=new CountDownLatch(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        log.info("testVersionBack4InsertMember 开始执行...");
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                QunMember c =new QunMember();
                c.setQunId(1);
                c.setUserId(System.currentTimeMillis()+Utils.getThreadId());
                insertMemberAndUpateQun(c,500,0,downLatch,cyclicBarrier);
            }
        });
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                //sleepBefore =10 ,保证比A晚开始执行
                QunMember d =new QunMember();
                d.setQunId(1);
                d.setUserId(System.currentTimeMillis()+Utils.getThreadId());
                insertMemberAndUpateQun(d,0,50,downLatch,cyclicBarrier);
            }
        });
        threadA.start();
        threadB.start();
        try {
            downLatch.await();
            log.info("testVersionBack4InsertMember 执行完毕！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void testDBVersionBack4InsertMember(){

        //两个线程分别执行
        CountDownLatch downLatch=new CountDownLatch(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        log.info("testVersionBack4InsertMember 开始执行...");
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                QunMember c =new QunMember();
                c.setQunId(1);
                c.setUserId(System.currentTimeMillis()+Utils.getThreadId());
                insertMemberAndUpateQunDBVersion(c,0,0,10000,downLatch,cyclicBarrier);
            }
        });
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                //sleepBefore =10 ,保证比A晚开始执行
                QunMember d =new QunMember();
                d.setQunId(1);
                d.setUserId(System.currentTimeMillis()+Utils.getThreadId());
                insertMemberAndUpateQunDBVersion(d,0,1000,0,downLatch,cyclicBarrier);
            }
        });
        threadA.start();
        threadB.start();
        try {
            downLatch.await();
            log.info("testVersionBack4InsertMember 执行完毕！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //测试群成员计数更新-幻读问题
    public void testMemberCount(){

        //两个线程分别执行
        CountDownLatch downLatch=new CountDownLatch(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        log.info("testMemberCount 开始执行...");
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                QunMember member =new QunMember();
                member.setQunId(1);
                member.setUserId(System.currentTimeMillis()+Utils.getThreadId());
                insertMemberAndUpateQunMemberCount(member,0,10,downLatch,cyclicBarrier);
            }
        });
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                //sleepBefore =10 ,保证比A晚开始执行
                updateMemberCount(qunId,500,0,downLatch,cyclicBarrier);
            }
        });
        threadA.start();
        threadB.start();
        try {
            downLatch.await();
            log.info("testMemberCount 执行完毕！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testDeadLock(){
        //两个线程分别执行
        CountDownLatch downLatch=new CountDownLatch(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        log.info("testDeadLock 开始执行...");
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                updateQunAndMember(qunId,memberA,500,0,downLatch,cyclicBarrier);
            }
        });
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                //sleepBefore =10 ,保证比A晚开始执行
                updateMemberAndQun(qunId,memberA,500,0,downLatch,cyclicBarrier);
            }
        });
        threadA.start();
        threadB.start();
        try {
            downLatch.await();
            log.info("testDeadLock 执行完毕！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void updateQunAndMember(long qunId,long memberId,long middleSleepMillis,long sleepBefore,CountDownLatch downLatch,CyclicBarrier cyclicBarrier){
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        Utils.sleep(sleepBefore);
        log.info(Utils.getThreadId()+"开始执行，更新");
        qunService.updateQunAndQunMember(qunId,memberId,middleSleepMillis);
        log.info(Utils.getThreadId()+"执行更新，完毕");
        downLatch.countDown();
    }
    private void updateMemberAndQun(long qunId,long memberId,long middleSleepMillis,long sleepBefore,CountDownLatch downLatch,CyclicBarrier cyclicBarrier){
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        Utils.sleep(sleepBefore);
        log.info(Utils.getThreadId()+"开始执行，更新");
        qunService.updateQunMemberAndQun(qunId,memberId,middleSleepMillis);
        log.info(Utils.getThreadId()+"执行更新，完毕");
        downLatch.countDown();
    }



    private void insertMemberAndUpateQun(QunMember member, long middleSleepMillis, long sleepBefore, CountDownLatch downLatch, CyclicBarrier cyclicBarrier){
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        Utils.sleep(sleepBefore);
        log.info(Utils.getThreadId()+"开始执行，更新");
        qunService.insertMemberAndUpdateQun(member,middleSleepMillis);
        log.info(Utils.getThreadId()+"执行更新，完毕");
        downLatch.countDown();
    }

    private void insertMemberAndUpateQunMemberCount(QunMember member, long middleSleepMillis, long sleepBefore, CountDownLatch downLatch, CyclicBarrier cyclicBarrier){
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        Utils.sleep(sleepBefore);
        log.info(Utils.getThreadId()+"开始执行，更新");
        qunService.insertMemberAndUpdateQunMemberCount(member,middleSleepMillis);
        log.info(Utils.getThreadId()+"执行更新，完毕");
        downLatch.countDown();
    }

    private void updateMemberCount(long qunId, long middleSleepMillis, long sleepBefore, CountDownLatch downLatch, CyclicBarrier cyclicBarrier){
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        Utils.sleep(sleepBefore);
        log.info(Utils.getThreadId()+"开始执行，更新");
        qunService.updateQunMemberCount(qunId,middleSleepMillis);
        log.info(Utils.getThreadId()+"执行更新，完毕");
        downLatch.countDown();
    }

    private void insertMemberAndUpateQunDBVersion(QunMember member, long middleSleepMillis, long sleepBefore, long sleepEnd, CountDownLatch downLatch, CyclicBarrier cyclicBarrier){
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        Utils.sleep(sleepBefore);
        log.info(Utils.getThreadId()+"开始执行，更新");
        qunService.insertMemberAndUpdateQunUseDbVersion(member,middleSleepMillis,sleepEnd);
        log.info(Utils.getThreadId()+"执行更新，完毕");
        downLatch.countDown();
    }


}
