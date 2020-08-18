package com.spx.hive_test.dto;

/*
 *  create by 01397553 Shipeixin on 2020/8/18
 */

import lombok.Data;

@Data
public class EmpDeptDTO {
/*
  e.empno	e.ename	e.sal	d.dname	d.loc

 */


    private Integer empno;

    private String empname;

    private Double empsal;

    private String detpname;

    private String deptloc;
}
