package com.example.demo.Dao;

import com.example.demo.Models.*;
import com.example.demo.repom.BusRepository;
import com.example.demo.repom.DriverRepository;
import com.example.demo.repom.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class WorkDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BusRepository busRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    DriverRepository driverRepository;

    public int save(String busname, String routename, String surname, Date datetime) {
        List<Bus> listBus = busRepository.findByName(busname);
        List<route> listRoute = routeRepository.findByName(routename);
        List<driver> listDriver = driverRepository.findByName(surname);
        if (listBus != null && listRoute != null && listDriver != null){
            for(Bus bus : listBus){
                return jdbcTemplate.update("INSERT INTO `work` (`Date`, `bus_id`, `driver_id`, `route_id`) VALUES (?, ?, ?, ?);",datetime, bus.getId(), listDriver.get(0).getId(), listRoute.get(0).getId());
            }
        } else {
            return 0;
        }
        return 0;
    }

    public int delete(Long id){
        return jdbcTemplate.update("DELETE FROM `work` WHERE `id`= ?",id);
    }

    public int Update(String busname, String routename, String surname, Date datetime, Long id){
        List<Bus> listBus = busRepository.findByName(busname);
        List<route> listRoute = routeRepository.findByName(routename);
        List<driver> listDriver = driverRepository.findByName(surname);
        if (listBus != null && listRoute != null && listDriver!= null){
            for(Bus bus : listBus){
                return jdbcTemplate.update("UPDATE `work` SET `Date` = ?, `bus_id` = ?, `driver_id` = ?, `route_id` = ? WHERE `work`.`id` = ?;",datetime, bus.getId(), listDriver.get(0).getId(), listRoute.get(0).getId(), id);
            }
        } else {
            return 0;
        }
        return 0;
    }

    public List<work> Search(String busname, String routename, String surname) {
        busname = "%"+busname+"%";
        routename = "%"+routename+"%";
        surname = "%"+surname+"%";
        if(busname.equals("%%") && surname.equals("%%") && !routename.equals("%%")){
            return jdbcTemplate.query(
                    "SELECT wr.`Date`, wr.`bus_id`, wr.`driver_id`, wr.`route_id`, wr.`id` FROM `work` wr INNER JOIN route ro ON(ro.ID = wr.`route_id`) WHERE ro.name LIKE ?",
                    new Object[]{routename},
                    new BeanPropertyRowMapper<>(work.class)
            );
        } else if(routename.equals("%%") && surname.equals("%%") && !busname.equals("%%")){
            return jdbcTemplate.query(
                    "SELECT wr.`Date`, wr.`bus_id`, wr.`driver_id`, wr.`route_id`, wr.`id` FROM `work` wr INNER JOIN bus bs ON(bs.ID = wr.`bus_id`) WHERE bs.name LIKE ?",
                    new Object[]{busname},
                    new BeanPropertyRowMapper<>(work.class)
            );
        } else if(routename.equals("%%") && busname.equals("%%") && !surname.equals("%%")){
            return jdbcTemplate.query(
                    "SELECT wr.`Date`, wr.`bus_id`, wr.`driver_id`, wr.`route_id`, wr.`id` FROM `work` wr INNER JOIN driver dr ON(dr.ID = wr.`driver_id`) WHERE dr.name LIKE ?",
                    new Object[]{surname},
                    new BeanPropertyRowMapper<>(work.class)
            );
        } else if(routename.equals("%%") && !busname.equals("%%") && !surname.equals("%%")){
            return jdbcTemplate.query(
                    "SELECT wr.`Date`, wr.`bus_id`, wr.`driver_id`, wr.`route_id`, wr.`id` FROM `work` wr INNER JOIN bus bs ON(bs.ID = wr.`bus_id`) INNER JOIN driver dr ON(dr.ID = wr.`driver_id`) where bs.name LIKE ? AND dr.name LIKE ?",
                    new Object[]{busname,surname},
                    new BeanPropertyRowMapper<>(work.class)
            );
        } else if(!routename.equals("%%") && busname.equals("%%") && !surname.equals("%%")){
            return jdbcTemplate.query(
                    "SELECT wr.`Date`, wr.`bus_id`, wr.`driver_id`, wr.`route_id`, wr.`id` FROM `work` wr INNER JOIN route ro ON(ro.ID = wr.`route_id`) INNER JOIN driver dr ON(dr.ID = wr.`driver_id`) where ro.name LIKE ? AND dr.name LIKE ?",
                    new Object[]{routename,surname},
                    new BeanPropertyRowMapper<>(work.class)
            );
        } else if(!routename.equals("%%") && !busname.equals("%%") && surname.equals("%%")){
            return jdbcTemplate.query(
                    "SELECT wr.`Date`, wr.`bus_id`, wr.`driver_id`, wr.`route_id`, wr.`id` FROM `work` wr INNER JOIN route ro ON(ro.ID = wr.`route_id`) INNER JOIN bus bs ON(bs.ID = wr.`bus_id`) where ro.name LIKE ? AND bs.name LIKE ?",
                    new Object[]{routename,busname},
                    new BeanPropertyRowMapper<>(work.class)
            );
        } else {
            return jdbcTemplate.query(
                    "SELECT wr.`Date`, wr.`bus_id`, wr.`driver_id`, wr.`route_id`, wr.`id` FROM `work` wr INNER JOIN route ro ON(ro.ID = wr.`route_id`) INNER JOIN bus bs ON(bs.ID = wr.`bus_id`) INNER JOIN driver dr ON(dr.ID = wr.`driver_id`) where ro.name LIKE ? AND bs.name LIKE ? AND dr.name LIKE ?",
                    new Object[]{routename,busname,surname},
                    new BeanPropertyRowMapper<>(work.class)
            );
        }
    }
}
