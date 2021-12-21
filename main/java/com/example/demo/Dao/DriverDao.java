package com.example.demo.Dao;


import com.example.demo.Models.driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DriverDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<driver> Search(String name){
        name = "%"+name+"%";
        return jdbcTemplate.query(
                "select * from driver where name LIKE ?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(driver.class)
        );
    }

    public int Update(Long id, String name){
        return jdbcTemplate.update("UPDATE driver SET name = ?  WHERE ID = ?", name,id);
    }
}
