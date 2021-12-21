package com.example.demo.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class work {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private java.util.Date Date;

    public work(java.util.Date date, Long bus_id, Long driver_id, Long route_id) {
        Date = date;
        this.bus_id = bus_id;
        this.driver_id = driver_id;
        this.route_id = route_id;
    }

    public work() {
    }

    private Long bus_id;
    private Long driver_id;
    private Long route_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public Long getBus_id() {
        return bus_id;
    }

    public void setBus_id(Long bus_id) {
        this.bus_id = bus_id;
    }

    public Long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Long driver_id) {
        this.driver_id = driver_id;
    }

    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }
}
