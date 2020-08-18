package com.spx.hive_test.rowmapper;

import com.spx.hive_test.dto.EmpDeptDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *  create by 01397553 Shipeixin on 2020/8/18
 */
@Configuration
public class EmpAndDeptRowMapper implements RowMapper<EmpDeptDTO> {


    @Override
    public EmpDeptDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        EmpDeptDTO empDeptDTO = new EmpDeptDTO();

        empDeptDTO.setEmpno(rs.getInt("e.empno"));
        empDeptDTO.setEmpname(rs.getString("e.ename"));
        empDeptDTO.setEmpsal(rs.getDouble("e.sal"));
        empDeptDTO.setDeptloc(rs.getString("d.loc"));
        empDeptDTO.setDetpname(rs.getString("d.dname"));

        return empDeptDTO;
    }
}
