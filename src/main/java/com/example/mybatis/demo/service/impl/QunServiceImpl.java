package com.example.mybatis.demo.service.impl;

import com.example.mybatis.demo.entity.Qun;
import com.example.mybatis.demo.entity.QunMember;
import com.example.mybatis.demo.mapper.QunMapper;
import com.example.mybatis.demo.mapper.QunMemberMapper;
import com.example.mybatis.demo.service.QunService;
import com.example.mybatis.demo.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * QunServiceImpl
 *
 * @author zhangchao01
 * @date 2021/11/11
 */
@Service
@Slf4j
public class QunServiceImpl  implements QunService {

    @Autowired
    private QunMapper qunMapper;
    @Autowired
    private QunMemberMapper qunMemberMapper;

    @Override
    @Transactional()
    public Qun createQun(Qun qun) {
        qunMapper.insert(qun);
        return qun;
    }

    @Override
    @Transactional()
    public QunMember createQunMember(QunMember qunMember) {
        qunMemberMapper.insert(qunMember);
        return qunMember;
    }

    @Override
    @Transactional()
    public int updateQunMemberAndQun(long qunId, long memberId, long middleSleepMillis) {
        return updateQunMemberAndQunInner(qunId,memberId,middleSleepMillis);
//        return updateQunMemberAndQun4FristQueryQun(qunId,memberId,middleSleepMillis);
    }

    @Override
    @Transactional()
    public void insertMemberAndUpdateQun(QunMember member, long middleSleepMillis) {
        long version =System.currentTimeMillis();
        String updateTransaction=Utils.getThreadId();
        Qun qun=qunMapper.selectById(member.getQunId());

        member.setVersion(version);
        member.setUpdateTransaction(updateTransaction);
        log.info("添加成员开始 member= {}",member);
        qunMemberMapper.insert(member);
        log.info("添加成员完成 member= {}",member);

        Utils.sleep(middleSleepMillis);

        qun.setVersion(version);
        qun.setUpdateTransaction(updateTransaction);
        log.info("更新群开始 qun= {}",qun);
        qunMapper.updateQunVersion(qun);
        log.info("更新群完成 qun= {}",qun);
    }

    @Override
    @Transactional()
    public void insertMemberAndUpdateQunMemberCount(QunMember member, long middleSleepMillis) {
        long version =System.currentTimeMillis();
        String updateTransaction=Utils.getThreadId();
        Qun qun=qunMapper.selectById(member.getQunId());

        member.setVersion(version);
        member.setUpdateTransaction(updateTransaction);
        log.info("添加成员开始 member= {}",member);
        qunMemberMapper.insert(member);
        log.info("添加成员完成 member= {}",member);

        Utils.sleep(middleSleepMillis);

        qun.setVersion(version);
        qun.setUpdateTransaction(updateTransaction);
        int memberCount = qunMemberMapper.memberCount(member.getQunId());
        qun.setMemberCount(memberCount);
        log.info("更新群开始 qun= {}",qun);
        qunMapper.updateQunMemberCount(qun);
        log.info("更新群完成 qun= {}",qun);
    }

    @Override
//    @Transactional( isolation = Isolation.REPEATABLE_READ)
    @Transactional( isolation = Isolation.READ_COMMITTED)
    public void updateQunMemberCount(long qunId, long middleSleepMillis) {
        long version =System.currentTimeMillis();
        String updateTransaction=Utils.getThreadId();
        Qun qun=qunMapper.selectById(qunId);
        qun.setVersion(version);
        qun.setUpdateTransaction(updateTransaction);
        int memberCount = qunMemberMapper.memberCount(qunId);
        log.info("查询成员计数完成 qun= {}，memberCount={}",qun,memberCount);

        Utils.sleep(middleSleepMillis);

        qun.setVersion(version);
        qun.setUpdateTransaction(updateTransaction);
        qun.setMemberCount(memberCount);
        log.info("更新群开始 qun= {}",qun);
        qunMapper.updateQunMemberCount(qun);
        log.info("更新群完成 qun= {}",qun);

        memberCount = qunMemberMapper.memberCount(qunId);
        log.info("事务提交前查询最新成员计数 qun= {}，memberCount={}",qun,memberCount);
    }

    @Override
    @Transactional()
    public int updateQunAndQunMember(long qunId, long memberId, long middleSleepMillis) {
        long version =System.currentTimeMillis();
        String updateTransaction=Utils.getThreadId();
        Qun qun=qunMapper.selectById(qunId);
        QunMember member= qunMemberMapper.selectById(memberId);
        member.setVersion(version);
        member.setUpdateTransaction(updateTransaction);

        qun.setVersion(version);
        qun.setUpdateTransaction(updateTransaction);
        log.info("更新群开始 qun= {}",qun);
        qunMapper.updateQunVersion(qun);
        log.info("更新群完成 qun= {}",qun);

        Utils.sleep(middleSleepMillis);

        log.info("更新成员开始 member= {}",member);
        qunMemberMapper.updateVersion(member);
        log.info("更新成员完成 member= {}",member);

        return 1;
    }

    private int updateQunMemberAndQunInner(long qunId, long memberId, long middleSleepMillis){
        long version =System.currentTimeMillis();
        String updateTransaction=Utils.getThreadId();

        QunMember member= qunMemberMapper.selectById(memberId);
        member.setVersion(version);
        member.setUpdateTransaction(updateTransaction);
        log.info("更新成员开始 member= {}",member);
        qunMemberMapper.updateVersion(member);
        log.info("更新成员完成 member= {}",member);

        Utils.sleep(middleSleepMillis);


        Qun qun=qunMapper.selectById(qunId);
        qun.setVersion(version);
        qun.setUpdateTransaction(updateTransaction);
        log.info("更新群开始 qun= {}",qun);
        qunMapper.updateQunVersion(qun);
        log.info("更新群完成 qun= {}",qun);
        return 1;
    }

    private int updateQunMemberAndQun4FristQueryQun(long qunId, long memberId, long middleSleepMillis){
        long version =System.currentTimeMillis();
        String updateTransaction=Utils.getThreadId();
        Qun qun=qunMapper.selectById(qunId);
        QunMember member= qunMemberMapper.selectById(memberId);
        member.setVersion(version);
        member.setUpdateTransaction(updateTransaction);
        log.info("更新成员开始 member= {}",member);
        qunMemberMapper.updateVersion(member);
        log.info("更新成员完成 member= {}",member);

        Utils.sleep(middleSleepMillis);


        //Qun qun=qunMapper.selectById(qunId);
        qun.setVersion(version);
        qun.setUpdateTransaction(updateTransaction);
        log.info("更新群开始 qun= {}",qun);
        qunMapper.updateQunVersion(qun);
        log.info("更新群完成 qun= {}",qun);
        return 1;
    }


    //    private String getUpdateTransacton(){
//        return "thread-"+Thread.currentThread().getId();
//    }
}
