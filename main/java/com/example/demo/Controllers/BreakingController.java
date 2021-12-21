package com.example.demo.Controllers;


import com.example.demo.Dao.BreakingDao;
import com.example.demo.Models.Bus;
import com.example.demo.Models.breaking;


import com.example.demo.repom.BreakingRepository;
import com.example.demo.repom.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BreakingController {

    @Autowired
    BreakingDao breakingDao;

    @Autowired
    BusRepository busRepository;

    @Autowired
    BreakingRepository breakingRepository;

    @GetMapping("/breaking")
    public String home(Model model) {
        List<breaking> breakinges = breakingDao.All();
        model.addAttribute("breakinges",breakinges);
        return "/breaking/breaking";
    }


    @GetMapping("/breaking/add")
    private String Add(Model model){
        return "/breaking/breaking_add";
    }


    @PostMapping("/breaking/ad")
    private String PostAdd(Model model, @RequestParam String name, @RequestParam String busname, @RequestParam String datetime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(datetime);
        int i = breakingDao.sav(name,busname,parsed);
        return "redirect:/breaking";
    }


    @GetMapping("/breaking/delite/{id}")
    public String Delite(@PathVariable(value = "id") Long id, Model model){
        breakingDao.delete(id);
        return "redirect:/breaking";
    }


    @GetMapping("/breaking/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model){
        if(!breakingRepository.existsById(id)){
            return "redirect:/bus";
        }
        Optional<breaking> breaking = breakingRepository.findById(id);
        ArrayList<breaking> rbreaking = new ArrayList<>();
        breaking.ifPresent(rbreaking::add);
        model.addAttribute("breakings",rbreaking);
        return "/breaking/breaking_edit";
    }

    @PostMapping("/breaking/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String busname, @RequestParam String datetime, Model model) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(datetime);
        int i = breakingDao.Update(name,busname,parsed,id);
        return "redirect:/breaking";
    }



    @GetMapping("/breaking/search")
    public String Search(@RequestParam String name,@RequestParam String busid, Model model){
        List<breaking> breaking = breakingDao.Search(name,busid);
        model.addAttribute("breakinges",breaking);
        return "/breaking/breaking";
    }


    @GetMapping("/breaking/formreport")
    public String Report(Model model) {
        return "/breaking/breaking_reportform";
    }

    @PostMapping("/breaking/breakingreport")
    private String ReportInfa(Model model, @RequestParam String busname, @RequestParam Long month,@RequestParam Long year) {
        List<breaking> breakinges = breakingDao.report(busname,month,year);
        model.addAttribute("breakinges",breakinges);
        return "/breaking/breaking_report";
    }
}
