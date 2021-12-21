package com.example.demo.Dao;

import com.example.demo.Models.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Bus> Search(String name) {
        name = "%"+name+"%";
        return jdbcTemplate.query(
                "select * from bus where name LIKE ?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(Bus.class)
        );
    }

    public int Update(Long id, String name, Long cost){
        return jdbcTemplate.update("UPDATE bus SET name = ? , cost = ? WHERE ID = ?", name,cost,id);
    }
}
