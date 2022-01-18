package com.example.mybatis.demo.service;

import com.example.mybatis.demo.entity.Qun;
import com.example.mybatis.demo.entity.QunMember;

/**
 * QunService
 *
 * @author zhangchao01
 * @date 2021/11/10
 */
public interface QunService {


    Qun createQun(Qun qun);
    QunMember createQunMember(QunMember qunMember);

   // 修改群成员 +群信息 +中间休息
    int updateQunMemberAndQun(long qunId,long memberId, long middleSleepMillis);

    void insertMemberAndUpdateQun(QunMember qunMember,long middleSleepMillis);

    void insertMemberAndUpdateQunMemberCount(QunMember qunMember,long middleSleepMillis);

    void updateQunMemberCount(long qunId,long middleSleepMillis);

    int updateQunAndQunMember(long qunId,long memberId, long middleSleepMillis);


    void insertMemberAndUpdateQunUseDbVersion(QunMember qunMember, long middleSleepMillis,long endSleepMillis);
}
