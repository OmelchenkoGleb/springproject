package com.example.demo.Controllers;


import com.example.demo.Dao.AdminDao;
import com.example.demo.Models.Bus;
import com.example.demo.Models.admin;
import com.example.demo.repom.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminDao adminDao;

    @GetMapping("/admin")
    public String home(Model model) {
//        Iterable<admin> admins = adminRepository.findAll();
        admin admin = adminRepository.findByLogin("gleb");


        model.addAttribute("admins",admin);
        return "/admin/admin";
    }


    @GetMapping("/admin/add")
    public String Add(Model model) {
        return "/admin/admin_add";
    }


    @PostMapping("/admin/ad")
    public String PostAdd(@ModelAttribute admin admin) {
        adminRepository.save(admin);
        return "redirect:/admin";
    }


    @GetMapping("/admin/search")
    public String Search(@RequestParam String login, Model model){
        List<admin> admins = adminDao.Search(login);
        model.addAttribute("admins",admins);
        return "/admin/admin";
    }



    @GetMapping("/admin/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, Model model){
        if(!adminRepository.existsById(id)){
            return "redirect:/admin";
        }
        Optional<admin> admin = adminRepository.findById(id);
        ArrayList<admin> admins = new ArrayList<>();
        admin.ifPresent(admins::add);
        model.addAttribute("admins",admins);
        return "/admin/admin_edit";
    }



    @PostMapping("/admin/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, @RequestParam String login, @RequestParam String pass, Model model){
        int i = adminDao.Update(id,login,pass);
        return "redirect:/admin";
    }



    @GetMapping("/admin/delite/{id}")
    public String Delite(@PathVariable(value = "id") Long id, Model model){
//        Bus bus = busRepository.findById(id).orElseThrow();
        adminRepository.deleteById(id);
        return "redirect:/admin";
    }
}
