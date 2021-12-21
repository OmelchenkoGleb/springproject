package com.example.demo.Controllers;

import com.example.demo.Dao.DriverDao;
import com.example.demo.Models.Bus;
import com.example.demo.Models.driver;
import com.example.demo.repom.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
public class DriverController {
    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DriverDao driverDao;

    @GetMapping("/driver")
    private String All(Model model){
        Iterable<driver> drivers = driverRepository.findAll();
        model.addAttribute("drivers",drivers);
        return "/driver/driver";
    }

    @GetMapping("/driver/add")
    private String Add(Model model){
        return "/driver/driver_add";
    }

    @PostMapping("/driver/ad")
    private String PostAdd(Model model, @RequestParam String name){
        driver driver = new driver(name);
        driverRepository.save(driver);
        return "redirect:/driver";
    }

    @GetMapping("/driver/search")
    private String Search(Model model, @RequestParam String name){
        List<driver> drivers = driverDao.Search(name);
        model.addAttribute("drivers",drivers);
        return "/driver/driver";
    }

    @GetMapping("/driver/delite/{id}")
    public String Delite(@PathVariable(value = "id") Long id, Model model){
        driverRepository.deleteById(id);
        return "redirect:/driver";
    }

    @GetMapping("/driver/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, Model model){
        if(!driverRepository.existsById(id)){
            return "redirect:/bus";
        }
        Optional<driver> driver = driverRepository.findById(id);
        ArrayList<driver> rdriver = new ArrayList<>();
        driver.ifPresent(rdriver::add);
        model.addAttribute("drivers",rdriver);
        return "/driver/driver_edit";
    }

    @PostMapping("/driver/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, @RequestParam String name, Model model){
        int i = driverDao.Update(id,name);
        return "redirect:/driver";
    }

    @GetMapping("/driver/info/{id}")
    public String info(@PathVariable(value = "id") Long id, Model model){
        if(!driverRepository.existsById(id)){
            return "redirect:/bus";
        }
        Optional<driver> driver = driverRepository.findById(id);
        ArrayList<driver> drivers = new ArrayList<>();
        driver.ifPresent(drivers::add);
        model.addAttribute("drivers",drivers);
        return "/driver/driver_info";
    }
}
