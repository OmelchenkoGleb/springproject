package com.example.demo.repom;

import com.example.demo.Models.driver;
import com.example.demo.Models.route;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriverRepository extends CrudRepository<driver,Long> {

    List<driver> findByName(String name);
}
