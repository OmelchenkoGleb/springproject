package com.example.demo.Controllers;


import com.example.demo.Dao.RouteDao;
import com.example.demo.Models.Bus;
import com.example.demo.Models.driver;
import com.example.demo.Models.route;
import com.example.demo.repom.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RouteController {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    RouteDao routeDao;

    @GetMapping("/route")
    private String All(Model model){
        Iterable<route> routes = routeRepository.findAll();
        model.addAttribute("routes",routes);
        return "/route/route";
    }

    @GetMapping("/route/add")
    private String Add(Model model){
        return "/route/route_add";
    }

    @PostMapping("/route/ad")
    private String PostAdd(Model model, @RequestParam String name){
        route route = new route(name);
        routeRepository.save(route);
        return "redirect:/route";
    }

    @GetMapping("/route/search")
    private String Search(Model model, @RequestParam String name){
        List<route> routes = routeDao.Search(name);
        model.addAttribute("routes",routes);
        return "/route/route";
    }

    @GetMapping("/route/delite/{id}")
    public String Delite(@PathVariable(value = "id") Long id, Model model){
        routeRepository.deleteById(id);
        return "redirect:/route";
    }

    @GetMapping("/route/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, Model model){
        if(!routeRepository.existsById(id)){
            return "redirect:/route";
        }
        Optional<route> route = routeRepository.findById(id);
        ArrayList<route> rroute = new ArrayList<>();
        route.ifPresent(rroute::add);
        model.addAttribute("routes",rroute);
        return "/route/route_edit";
    }

    @PostMapping("/route/edit/{id}")
    public String Edit(@PathVariable(value = "id") Long id, @RequestParam String name, Model model){
        int i = routeDao.Update(id,name);
        return "redirect:/route";
    }


    @GetMapping("/route/info/{id}")
    public String info(@PathVariable(value = "id") Long id, Model model){
        if(!routeRepository.existsById(id)){
            return "redirect:/route";
        }
        Optional<route> route = routeRepository.findById(id);
        ArrayList<route> routee = new ArrayList<>();
        route.ifPresent(routee::add);
        model.addAttribute("routes",routee);
        return "/route/route_info";
    }

}
