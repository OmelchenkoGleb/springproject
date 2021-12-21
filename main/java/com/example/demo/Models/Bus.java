package com.example.demo.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bus")
public class Bus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Long cost;






//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bus")
//    private List<breaking> breakings;
//    public List<breaking> getBreakings() {
//        return breakings;
//    }
//
//    public void setBreakings(List<breaking> breakings) {
//        this.breakings = breakings;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }


    public Bus(String name, Long cost) {
        this.name = name;
        this.cost = cost;
    }

    public Bus() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}


