package com.spx.hive_test.controller;

import com.spx.hive_test.dto.EmpDeptDTO;
import com.spx.hive_test.rowmapper.DeptRowMapper;
import com.spx.hive_test.rowmapper.EmpAndDeptRowMapper;
import com.spx.hive_test.rowmapper.EmpRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/*
 *  create by 01397553 Shipeixin on 2020/8/17
 */
@RestController
public class HiveController {

    public static final Logger log = LoggerFactory.getLogger(HiveController.class);

    @Autowired
    @Qualifier(value = "hiveDruidDataSource")
    DataSource hiveDataSource;

    @Autowired
    JdbcTemplate hiveJdbcTemplate;

    @Autowired
    DeptRowMapper deptRowMapper;

    @Autowired
    EmpRowMapper empRowMapper;

    @Autowired
    EmpAndDeptRowMapper empAndDeptRowMapper;

    @RequestMapping("/getTables")
    public List<String> getTables()  {

        Long start = System.currentTimeMillis();
        List<String> list = new ArrayList<>();
            String sql = "show tables ";
            log.info("execute sql is " + "=======>"+sql);
            list = hiveJdbcTemplate.query(sql, new SingleColumnRowMapper<>());

        log.info("耗时：" + (System.currentTimeMillis() - start) + "毫秒");
        return list;
    }



    @RequestMapping("/createTable")
    public void createTable(){
        Long start = System.currentTimeMillis();
        String sql = "CREATE TABLE dept(\n" +
                "deptno int,\n" +
                "dname string,\n" +
                "loc string\n" +
                ") ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\t'";
        hiveJdbcTemplate.execute(sql);
        log.info("耗时：" + (System.currentTimeMillis() - start) + "毫秒");
    }


    @RequestMapping("/query/{tableName}")
    public Object query(@PathVariable("tableName") String tableName){

        Long start = System.currentTimeMillis();
        List list = new ArrayList<>();

        String sql = "select * from "+ tableName;
        if ("emp".equals(tableName)){
            list = hiveJdbcTemplate.query(sql,empRowMapper);
        }else if ("dept".equals(tableName)){
            list = hiveJdbcTemplate.query(sql,deptRowMapper);
        }

        log.info("耗时 ————>"+ (System.currentTimeMillis() - start) + "毫秒" );
        return list;
    }

    @RequestMapping("empAverageSal")
    public Double queryDeptCount(){
        Long start = System.currentTimeMillis();
        String sql = "select avg(sal) from emp";
        List<Double> query = hiveJdbcTemplate.query(sql, new SingleColumnRowMapper<>(Double.class));
        log.info("耗时 ————>"+ (System.currentTimeMillis() - start) + "毫秒" );
        return query.get(0);

    }

    @RequestMapping("/queryByHQL/{HQL}")
    public Object acceptHQL(@PathVariable("HQL") String HQL){

        return null;
    }

    @RequestMapping("/empAndDept")
    public List<EmpDeptDTO> empAndDept(){
        String sql = "select e.empno,e.ename,e.sal,d.dname,d.loc from emp e inner join dept d on e.empno = 1 and e.deptno = d.deptno";

        List<EmpDeptDTO> list = hiveJdbcTemplate.query(sql, empAndDeptRowMapper);

        return list;
    }
}
