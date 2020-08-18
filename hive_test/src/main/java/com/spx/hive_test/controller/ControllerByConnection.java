package com.spx.hive_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  create by 01397553 Shipeixin on 2020/8/18
 */

@RestController
public class ControllerByConnection {

    @Autowired
    @Qualifier(value = "hiveDruidDataSource")
    DataSource hiveDataSource;
    Statement statement;


    @RequestMapping("/test")
    public List<Map<String,Object>> test() throws SQLException {
        List<Map<String,Object>> list = null;
        Connection con = hiveDataSource.getConnection();
         statement = con.createStatement();
         //String sql = "select * from emp";
         String sql = "select e.empno,e.ename,e.sal,d.dname,d.loc from emp e inner join dept d on e.empno = 1 and e.deptno = d.deptno";
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();

        list = new ArrayList<>();

        //  获得 resultSet 中 一行列的总和
        int columnCount = metaData.getColumnCount();

        // 装载每一行的数据
        while(resultSet.next()) {

                HashMap<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object obj = resultSet.getObject(i);
                    map.put(columnName, obj);
                }
                list.add(map);

        }

        return list;

    }
}
