package com.example.mybatis.demo.mapper;

import com.example.mybatis.demo.entity.Qun;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * QunMapper
 *
 * @author zhangchao01
 * @date 2021/11/10
 */
@Mapper
public interface QunMapper {
//extends BaseMapper<Qun>
//    int updateQunVersion(long version,);

    int updateQunVersion(Qun qun);
//    int updateQunVersion(long version);

    int insert(Qun qun);

    Qun selectById(long id);

    int updateQunMemberCount(Qun qun);

//    #{name},#{memberCount},#{version},,#{updateTransaction}
//    int insert(String name,int memberCount,long version,String updateTransaction);

}
