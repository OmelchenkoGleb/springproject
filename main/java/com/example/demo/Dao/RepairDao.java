package com.example.demo.Dao;


import com.example.demo.Models.repai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RepairDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<repai> Search(String name) {
        name = "%"+name+"%";
        return jdbcTemplate.query(
                "select * from repai where name LIKE ?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(repai.class)
        );
    }

    public int Update(Long id, String name, Long cost){
        return jdbcTemplate.update("UPDATE repai SET name = ? , consumption = ? WHERE ID = ?", name,cost,id);
    }

}
