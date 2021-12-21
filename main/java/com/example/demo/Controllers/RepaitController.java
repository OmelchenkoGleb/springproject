package com.example.demo.Controllers;

import com.example.demo.Dao.RepairDao;
import com.example.demo.Models.repai;
import com.example.demo.repom.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RepaitController {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private RepairDao repairDao;



    @GetMapping("/repair")
    public String home(Model model) {
        Iterable<repai> repairs = repairRepository.findAll();
        model.addAttribute("repairs",repairs);
        return "/repair/repair";
    }

    @GetMapping("/repair/add")
    public String Add(Model model) {
        return "/repair/repair_add";
    }


    @PostMapping("/repair/ad")
    public String PostAdd(@ModelAttribute repai repair) {
        repairRepository.save(repair);
        return "redirect:/repair";
    }


    @GetMapping("/repair/search")
    public String Search(@RequestParam String name, Model model){
        List<repai> repairs = repairDao.Search(name);
        model.addAttribute("repairs",repairs);
        return "/repair/repair";
    }





    @GetMapping("/repair/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, Model model){
        if(!repairRepository.existsById(id)){
            return "redirect:/repair";
        }
        Optional<repai> repair = repairRepository.findById(id);
        ArrayList<repai> repairs = new ArrayList<>();
        repair.ifPresent(repairs::add);
        model.addAttribute("buses",repairs);
        return "/repair/repair_edit";
    }



    @PostMapping("/repair/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam Long cost, Model model){
        int i = repairDao.Update(id,name,cost);
        return "redirect:/repair";
    }



    @GetMapping("/repair/delite/{id}")
    public String Delite(@PathVariable(value = "id") Long id, Model model){
        repairRepository.deleteById(id);
        return "redirect:/repair";
    }


    @GetMapping("/repair/info/{id}")
    public String info(@PathVariable(value = "id") Long id, Model model){
        if(!repairRepository.existsById(id)){
            return "redirect:/repair";
        }
        Optional<repai> repair = repairRepository.findById(id);
        ArrayList<repai> repairs = new ArrayList<>();
        repair.ifPresent(repairs::add);
        model.addAttribute("repairs",repairs);
        return "/repair/repair_info";
    }

}
