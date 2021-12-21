package com.example.demo.repom;
import com.example.demo.Models.Bus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusRepository extends CrudRepository <Bus,Long>{
    List<Bus> findByName(String name);



    @Override
    <S extends Bus> S save(S entity);
}
