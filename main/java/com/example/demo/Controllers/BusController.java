package com.example.demo.Controllers;

import com.example.demo.Dao.BusDao;
import com.example.demo.Models.Bus;
import com.example.demo.repom.BusRepository;
import com.example.demo.util.BusValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BusController {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusDao userDao;

    BusValidator busValidator;

    @GetMapping("/bus")
    public String home(Model model) {
        Iterable<Bus> buses = busRepository.findAll();
        model.addAttribute("buses",buses);
        return "/bus/bus";
    }

    @GetMapping("/bus/add")
    public String Add(Model model) {
        return "/bus/bus_add";
    }


    @PostMapping("/bus/ad")
    public String PostAdd(@ModelAttribute Bus bus) {
        busRepository.save(bus);
        return "redirect:/bus";
    }


    @GetMapping("/bus/search")
    public String Search(@RequestParam String name, Model model){
        List<Bus> buses = userDao.Search(name);
//        List<Bus> buses =  busRepository.findByName(name);
        model.addAttribute("buses",buses);
        return "/bus/bus";
    }





    @GetMapping("/bus/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, Model model){
        if(!busRepository.existsById(id)){
            return "redirect:/bus";
        }
        Optional<Bus> bus = busRepository.findById(id);
        ArrayList<Bus> rbus = new ArrayList<>();
        bus.ifPresent(rbus::add);
        model.addAttribute("buses",rbus);
        return "/bus/bus_edit";
    }



    @PostMapping("/bus/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam Long cost, Model model){
        int i = userDao.Update(id,name,cost);
        return "redirect:/bus";
    }



    @GetMapping("/bus/delite/{id}")
    public String Delite(@PathVariable(value = "id") Long id, Model model){
//        Bus bus = busRepository.findById(id).orElseThrow();
        busRepository.deleteById(id);
        return "redirect:/bus";
    }


    @GetMapping("/bus/info/{id}")
    public String info(@PathVariable(value = "id") Long id, Model model){
        if(!busRepository.existsById(id)){
            return "redirect:/bus";
        }
        Optional<Bus> bus = busRepository.findById(id);
        ArrayList<Bus> rbus = new ArrayList<>();
        bus.ifPresent(rbus::add);
        model.addAttribute("buses",rbus);
        return "/bus/bus_info";
    }
}
