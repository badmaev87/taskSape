package com.example.ClientTaskSape;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CsvFileUploaderApplication.class})
public class DBtest {

    private static String JDBC_URL ="jdbc:h2:mem:testdb";
    private static DataSource dataSource = null;

    public static DataSource getDataSource() {
        return dataSource;
    }

    @BeforeClass
    public static void initDb() throws SQLException {

        dataSource = JdbcConnectionPool.create(JDBC_URL, "sa", "");
        Connection conn = getDataSource().getConnection();
        Statement stat = conn.createStatement();

        stat.execute("CREATE TABLE TEST AS SELECT * FROM CSVREAD('src/main/resources/Client.csv')");
        try {
            ResultSet rs = stat.executeQuery("select phone from test where age<30");
            while (rs.next()) {
                System.out.println(rs.getString("phone"));
            }
        } catch (SQLException exception) {
            System.out.println(exception.getErrorCode());
        }
    }

    }

