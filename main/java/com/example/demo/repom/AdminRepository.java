package com.example.demo.repom;

import com.example.demo.Models.admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepository extends CrudRepository<admin, Long> {
    admin findByLogin (String login);
 }
