package com.spx.hive_test.rowmapper;

import com.spx.hive_test.domain.Dept;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/*
 *  create by 01397553 Shipeixin on 2020/8/18
 */
@Configuration
public class DeptRowMapper implements RowMapper<Dept> {
    @Override
    public Dept mapRow(ResultSet rs, int rowNum) throws SQLException {

        Dept dept = new Dept();

        dept.setDeptno(rs.getInt("dept.deptno"));
        dept.setDname(rs.getString("dept.dname"));
        dept.setLoc(rs.getString("dept.loc"));

        return dept;
    }
}
