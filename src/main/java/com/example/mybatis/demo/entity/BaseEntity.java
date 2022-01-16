package com.example.mybatis.demo.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * BaseEntity
 *
 * @author zhangchao01
 * @date 2021/11/11
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private long version;
    private String updateTransaction;

}
