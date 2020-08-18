package com.spx.hive_test.domain;

/*
 *  create by 01397553 Shipeixin on 2020/8/18
 */

import lombok.Data;

@Data
public class Emp {

    private Integer empno;

    private String ename;

    private String hiredate;

    private Double sal;

    private  Double comm;

    private Integer deptno;

    private String job;
}
