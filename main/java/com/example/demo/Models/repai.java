package com.example.demo.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class repai {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public repai(String name, Long consumption) {
        this.name = name;
        this.consumption = consumption;
    }

    public repai() {
    }

    private String name;
    private Long consumption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Long getConsumption() {
        return consumption;
    }

    public void setConsumption(Long consumption) {
        this.consumption = consumption;
    }
}
