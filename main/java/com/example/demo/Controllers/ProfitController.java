package com.example.demo.Controllers;


import com.example.demo.Dao.ProfitDao;
import com.example.demo.Models.Bus;
import com.example.demo.Models.breaking;
import com.example.demo.Models.profit;
import com.example.demo.repom.BusRepository;
import com.example.demo.repom.ProfitRepository;
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
public class ProfitController {

    @Autowired
    ProfitDao profitDao;

    @Autowired
    BusRepository busRepository;

    @Autowired
    ProfitRepository profitRepository;

    @GetMapping("/profit")
    public String home(Model model) {
        List<profit> profits = profitDao.All();
        model.addAttribute("profits",profits);
        return "/profit/profit";
    }


    @GetMapping("/profit/add")
    private String Add(Model model){
        return "/profit/profit_add";
    }


    @PostMapping("/profit/ad")
    private String PostAdd(Model model, @RequestParam String busname, @RequestParam String routename, @RequestParam String datetime, @RequestParam Long profit) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(datetime);
        int i = profitDao.save(busname,routename,parsed, profit);
        return "redirect:/profit";
    }

    @GetMapping("/profit/delite/{id}")
    public String Delite(@PathVariable(value = "id") Long id, Model model){
        profitDao.delete(id);
        return "redirect:/profit";
    }

    @GetMapping("/profit/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model){
        if(!profitRepository.existsById(id)){
            return "redirect:/profit";
        }
        Optional<profit> profit = profitRepository.findById(id);
        ArrayList<profit> profits = new ArrayList<>();
        profit.ifPresent(profits::add);
        model.addAttribute("profits",profits);
        return "/profit/profit_edit";
    }

    @PostMapping("/profit/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, Model model, @RequestParam String busname, @RequestParam String routename, @RequestParam String datetime, @RequestParam Long profit) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(datetime);
        int i = profitDao.Update(busname,routename,parsed, profit,id);
        return "redirect:/profit";
    }

    @GetMapping("/profit/search")
    public String Search(@RequestParam String busname,@RequestParam String routename, Model model){
        List<profit> profits = profitDao.Search(busname,routename);
        model.addAttribute("profits",profits);
        return "/profit/profit";
    }






    @GetMapping("/profit/formreport")
    public String Report(Model model) {
        return "/profit/profit_reportform";
    }

    @PostMapping("/profit/profitreport")
    private String ReportInfa(Model model, @RequestParam String busname, @RequestParam Long month,@RequestParam Long year) {
        List<profit> profits = profitDao.report(busname,month,year);
        model.addAttribute("profits",profits);
        return "/profit/profit_report";
    }
}
