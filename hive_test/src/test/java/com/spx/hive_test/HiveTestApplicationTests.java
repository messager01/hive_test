package com.spx.hive_test;

import com.spx.hive_test.config.HiveDruidConfig;
import com.sun.xml.internal.messaging.saaj.util.SAAJUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
class HiveTestApplicationTests {

    @Autowired
    HiveDruidConfig hiveDruidConfig;

    @Autowired
    @Qualifier(value = "hiveDruidDataSource")
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {

        Statement statement = dataSource.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("select * from emp");
while(resultSet.next()) {
   String id = resultSet.getString(1);
   System.out.println(id);
}
        System.out.println(resultSet);
    }

}
