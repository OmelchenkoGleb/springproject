package com.example.demo.Dao;

import com.example.demo.Models.route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<route> Search(String name){
        name = "%"+name+"%";
        return jdbcTemplate.query(
                "select * from route where name LIKE ?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(route.class)
        );
    }

    public int Update(Long id, String name){
        return jdbcTemplate.update("UPDATE route SET name = ?  WHERE ID = ?", name,id);
    }
}
