package com.example.demo.Models;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profit")
public class profit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long bus_id;
    private Date date;
    private Long route_id;
    private Long profit;


    public profit(Long bus_id, Date date, Long route_id, Long profit) {
        this.bus_id = bus_id;
        this.date = date;
        this.route_id = route_id;
        this.profit = profit;
    }

    public profit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getBus_id() {
        return bus_id;
    }

    public void setBus_id(Long bus_id) {
        this.bus_id = bus_id;
    }

    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }
}
