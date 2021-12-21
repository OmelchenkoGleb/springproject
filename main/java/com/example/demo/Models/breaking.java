package com.example.demo.Models;

import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;




@Entity
@Table(name = "breaking")
public class breaking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Date")
    private Date Date;

    @Column(name = "repair_id")
    private Long repair_id;

    @Column(name = "bus_id")
    private long bus_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(Long repair_id) {
        this.repair_id = repair_id;
    }


    //    @ToString.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bus_id", referencedColumnName = "id")
//    private Bus bus;
//    public Bus getBus() {
//        return bus;
//    }
//
//    public void setBus(Bus bus) {
//        this.bus = bus;
//    }

    public long getBus_id() {
        return bus_id;
    }
    public void setBus_id(long bus_id) {
        this.bus_id = bus_id;
    }
    public java.util.Date getDate() {
        return Date;
    }
    public void setDate(java.util.Date date) {
        Date = date;
    }


}


