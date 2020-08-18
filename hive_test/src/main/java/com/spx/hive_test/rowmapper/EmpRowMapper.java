package com.spx.hive_test.rowmapper;

import com.spx.hive_test.domain.Emp;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *  create by 01397553 Shipeixin on 2020/8/18
 */
@Component
public class EmpRowMapper implements RowMapper<Emp> {

    @Override
    public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {

        Emp emp = new Emp();

        emp.setEmpno(rs.getInt("emp.empno"));
        emp.setEname(rs.getString("emp.ename"));
        emp.setHiredate(rs.getString("emp.hiredate"));
        emp.setSal(rs.getDouble("emp.sal"));
        emp.setComm(rs.getDouble("emp.comm"));
        emp.setDeptno(rs.getInt("emp.deptno"));
        emp.setJob(rs.getString("emp.job"));

        return emp;
    }
}
