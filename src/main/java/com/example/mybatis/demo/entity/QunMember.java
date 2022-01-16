package com.example.mybatis.demo.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * QunMember
 *
 * @author zhangchao01
 * @date 2021/11/10
 */
@Data
@NoArgsConstructor
public class QunMember{
    private long id;
    private long version;
    private String updateTransaction;
    private long qunId;
    private String userId;

}
