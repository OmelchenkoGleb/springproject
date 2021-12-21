package com.example.demo.Controllers;


import com.example.demo.Dao.WorkDao;
import com.example.demo.Models.profit;
import com.example.demo.Models.route;
import com.example.demo.Models.work;
import com.example.demo.repom.WorkRepository;
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
public class WorkController {

    @Autowired
    WorkRepository workRepository;

    @Autowired
    WorkDao workDao;

    @GetMapping("/work")
    private String All(Model model){
        Iterable<work> works = workRepository.findAll();
        model.addAttribute("works",works);
        return "/work/work";
    }


    @GetMapping("/work/add")
    private String Add(Model model){
        return "/work/work_add";
    }


    @PostMapping("/work/ad")
    private String PostAdd(Model model, @RequestParam String busname, @RequestParam String routename, @RequestParam String surname, @RequestParam String datetime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(datetime);
        int i = workDao.save(busname,routename,surname,parsed);
        return "redirect:/work";
    }

    @GetMapping("/work/delite/{id}")
    public String Delite(@PathVariable(value = "id") Long id, Model model){
        workDao.delete(id);
        return "redirect:/work";
    }

    @GetMapping("/work/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model){
        if(!workRepository.existsById(id)){
            return "redirect:/work";
        }
        Optional<work> work = workRepository.findById(id);
        ArrayList<work> works = new ArrayList<>();
        work.ifPresent(works::add);
        model.addAttribute("works",works);
        return "/work/work_edit";
    }

    @PostMapping("/work/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, Model model, @RequestParam String busname, @RequestParam String routename, @RequestParam String surname, @RequestParam String datetime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(datetime);
        int i = workDao.Update(busname,routename,surname,parsed, id);
        return "redirect:/work";
    }

    @GetMapping("/work/search")
    public String Search(@RequestParam String busname , @RequestParam String surname, @RequestParam String routename, Model model){
        List<work> works = workDao.Search(busname,routename,surname);
        model.addAttribute("works",works);
        return "/work/work";
    }
}
