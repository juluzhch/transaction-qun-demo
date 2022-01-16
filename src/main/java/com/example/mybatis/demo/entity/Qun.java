package com.example.mybatis.demo.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Qun
 *
 * @author zhangchao01
 * @date 2021/11/10
 */
@Data
@NoArgsConstructor
public class Qun  {
    private long id;
    private long version;
    private String updateTransaction;
    private String name;
    private int memberCount;


}
