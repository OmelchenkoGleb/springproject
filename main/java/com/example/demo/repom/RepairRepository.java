package com.example.demo.repom;


import com.example.demo.Models.repai;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepairRepository extends CrudRepository<repai,Long> {
    List<repai> findByName(String name);
}
