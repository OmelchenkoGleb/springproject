package com.example.demo.util;

import com.example.demo.Dao.BusDao;
import com.example.demo.Models.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class BusValidator implements Validator {
    @Autowired
    BusDao busDao;


    @Override
    public boolean supports(Class<?> clazz) {
        return Bus.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Bus bus = (Bus) target;
        if (busDao.Search(bus.getName()) != null){
            errors.rejectValue("name", "", "Это название уже занято");
        }
    }
}
