package com.example.demo.repom;

import com.example.demo.Models.route;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteRepository extends CrudRepository<route,Long> {
    List<route> findByName(String name);
}
