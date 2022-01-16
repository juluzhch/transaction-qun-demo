package com.example.mybatis.demo.mapper;

import com.example.mybatis.demo.entity.Qun;
import com.example.mybatis.demo.entity.QunMember;
import org.apache.ibatis.annotations.Mapper;

/**
 * QunMemberMapper
 *
 * @author zhangchao01
 * @date 2021/11/10
 */
@Mapper
public interface QunMemberMapper {
    QunMember selectById(long id);
    int insert(QunMember qunMember);
    int updateVersion(QunMember qunMember);

    int memberCount(long qunId);
}
