package com.example.demo.Dao;


import com.example.demo.Models.Bus;
import com.example.demo.Models.breaking;
import com.example.demo.Models.repai;
import com.example.demo.repom.BreakingRepository;
import com.example.demo.repom.BusRepository;
import com.example.demo.repom.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;


@Component
public class BreakingDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BusRepository busRepository;

    @Autowired
    RepairRepository repairRepository;

    @Autowired
    BreakingRepository breakingRepository;

    public List All() {
        String sql = "SELECT * FROM breaking";
        List<breaking> breaking = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(breaking.class));
        return breaking;
    }



    public int delete(Long id){
        return jdbcTemplate.update("DELETE FROM `breaking` WHERE `id`= ?",id);
    }



    public int sav(String name, String busname, Date datetime){
        List<Bus> listBus = busRepository.findByName(busname);
        List<repai> listRepair = repairRepository.findByName(name);
        if (listBus != null && listRepair != null){
            for(Bus bus : listBus){
                return jdbcTemplate.update("INSERT INTO `breaking` (`Date`, `repair_id`, `bus_id`) VALUES (?, ?, ?)",datetime, listRepair.get(0).getId(), bus.getId());
            }
        } else {
            return 0;
        }
        return 0;
    }

    public int Update(String name, String busname, Date datetime,Long id){
        List<Bus> listBus = busRepository.findByName(busname);
        List<repai> listRepair = repairRepository.findByName(name);
        if (listBus != null && listRepair != null){
            for(Bus bus : listBus){
                    return jdbcTemplate.update("UPDATE `breaking` SET `Date` = ?, `repair_id` = ?, `bus_id` = ? WHERE `breaking`.`id` = ?;",datetime, listRepair.get(0).getId(),bus.getId() , id);
            }
        } else {
            return 0;
        }
        return 0;
    }


    public List<breaking> Search(String name,String busid) {
        name = "%"+name+"%";
        busid = "%"+busid+"%";
        if(busid.equals("%%") && !name.equals("%%")){
            return jdbcTemplate.query(
                    "select br.`id`,br.`Date`,br.`bus_id`,br.`repair_id` from breaking br INNER JOIN repai rp ON(br.`repair_id` = rp.ID) where rp.name LIKE ?",
                    new Object[]{name},
                    new BeanPropertyRowMapper<>(breaking.class)
            );
        } else if(name.equals("%%") && !busid.equals("%%")){
            return jdbcTemplate.query(
                    "select br.`id`,br.`Date`,br.`bus_id`,br.`repair_id` from breaking br INNER JOIN bus rp ON(br.`bus_id` = rp.ID) where rp.name LIKE ?",
                    new Object[]{busid},
                    new BeanPropertyRowMapper<>(breaking.class)
            );
        } else {
            return jdbcTemplate.query(
                    "select br.`id`,br.`Date`,br.`bus_id`,br.`repair_id` from breaking br INNER JOIN bus bs ON(br.`bus_id` = bs.ID) INNER JOIN repai rp ON(br.`repair_id` = rp.ID) where bs.name LIKE ? AND rp.name LIKE ?",
                    new Object[]{busid,name},
                    new BeanPropertyRowMapper<>(breaking.class)
            );
        }
    }

    public List<breaking> report(String busname, Long month, Long year) {
        return jdbcTemplate.query(
                "SELECT SUM(rp.consumption) as `repair_id`,MONTH(br.Date) as `bus_id` FROM breaking br INNER JOIN repai rp ON(br.repair_id = rp.ID) INNER JOIN bus bs ON(br.bus_id = bs.ID) WHERE bs.name = ? AND MONTH(br.Date) = ? AND YEAR(br.Date) = ? GROUP BY rp.consumption",
                new Object[]{busname,month,year},
                new BeanPropertyRowMapper<>(breaking.class)
        );
    }
}

