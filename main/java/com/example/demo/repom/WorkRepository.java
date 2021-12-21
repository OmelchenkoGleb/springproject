package com.example.demo.repom;

import com.example.demo.Models.work;
import org.springframework.data.repository.CrudRepository;

public interface WorkRepository extends CrudRepository<work,Long> {
}
