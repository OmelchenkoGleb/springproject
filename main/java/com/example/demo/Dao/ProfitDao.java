package com.example.demo.Dao;


import com.example.demo.Models.*;
import com.example.demo.repom.BusRepository;
import com.example.demo.repom.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ProfitDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BusRepository busRepository;

    @Autowired
    RouteRepository routeRepository;

    public List All() {
        String sql = "SELECT * FROM profit";
        List<profit> profit = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(profit.class));
        return profit;
    }

    public int save(String busname, String routename, Date datetime, Long profit) {
        List<Bus> listBus = busRepository.findByName(busname);
        List<route> listRoute = routeRepository.findByName(routename);
        if (listBus != null && listRoute != null){
            for(Bus bus : listBus){
                return jdbcTemplate.update("INSERT INTO `profit` (`Date`, `bus_id`, `route_id`, `profit`) VALUES (?, ?, ?, ?)",datetime, bus.getId(), listRoute.get(0).getId(), profit);
            }
        } else {
            return 0;
        }
        return 0;
    }

    public int delete(Long id){
        return jdbcTemplate.update("DELETE FROM `profit` WHERE `id`= ?",id);
    }


    public int Update(String busname, String routename, Date datetime, Long profit,Long id){
        List<Bus> listBus = busRepository.findByName(busname);
        List<route> listRoute = routeRepository.findByName(routename);
        if (listBus != null && listRoute != null){
            for(Bus bus : listBus){
                return jdbcTemplate.update("UPDATE `profit` SET `Date` = ?, `bus_id` = ?, `route_id` = ?, `profit` = ? WHERE `profit`.`id` = ?",datetime, bus.getId(), listRoute.get(0).getId(), profit, id);
            }
        } else {
            return 0;
        }
        return 0;
    }

    public List<profit> Search(String busname,String routename) {
        busname = "%"+busname+"%";
        routename = "%"+routename+"%";
        if(busname.equals("%%") && !routename.equals("%%")){
            return jdbcTemplate.query(
                    "select pr.`Date`, pr.`bus_id`, pr.`route_id`, pr.`profit` from profit pr INNER JOIN route ro ON(pr.`route_id` = ro.ID) where ro.name LIKE ?",
                    new Object[]{routename},
                    new BeanPropertyRowMapper<>(profit.class)
            );
        } else if(routename.equals("%%") && !busname.equals("%%")){
            return jdbcTemplate.query(
                    "select pr.`Date`, pr.`bus_id`, pr.`route_id`, pr.`profit` from profit pr INNER JOIN bus bs ON(pr.`bus_id` = bs.ID) where bs.name LIKE ?",
                    new Object[]{busname},
                    new BeanPropertyRowMapper<>(profit.class)
            );
        } else {
            return jdbcTemplate.query(
                    "select pr.`Date`, pr.`bus_id`, pr.`route_id`, pr.`profit` from profit pr INNER JOIN bus bs ON(pr.`bus_id` = bs.ID) INNER JOIN route ro ON(pr.`route_id` = ro.ID) where bs.name LIKE ? AND ro.name LIKE ?",
                    new Object[]{busname,routename},
                    new BeanPropertyRowMapper<>(profit.class)
            );
        }
    }

    public List<profit> report(String busname, Long month, Long year) {
        return jdbcTemplate.query(
                "SELECT SUM(pr.`profit`) as `route_id`,MONTH(pr.Date) as `bus_id` FROM profit pr INNER JOIN route ro ON(pr.`route_id` = ro.ID) INNER JOIN bus bs ON(pr.bus_id = bs.ID) WHERE bs.name = ? AND MONTH(pr.Date) = ? AND YEAR(pr.Date) = ?",
                new Object[]{busname,month,year},
                new BeanPropertyRowMapper<>(profit.class)
        );
    }
}
