package com.example.ClientTaskSape.Repository;

import com.example.ClientTaskSape.Entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ClientJDBCRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class ClientRowMapper implements RowMapper<Client> {
        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setName(rs.getString("name"));
            client.setAge(rs.getString("age"));
            client.setGroupId(rs.getString("group_id"));
            client.setPhone(rs.getString("phone"));
            return client;
        }
    }

    public List<Client> findAll() {
        return jdbcTemplate.query("select * from Client", new ClientRowMapper());
    }

    public int insert(String name, String age, String group_id, String phone, Date date) {
        return jdbcTemplate.update("insert into Client (name, age, group_id, phone, date) " + "values(?, ?, ?, ?, ?)",
                new Object[]{name, age, group_id, phone, date});
    }

}
