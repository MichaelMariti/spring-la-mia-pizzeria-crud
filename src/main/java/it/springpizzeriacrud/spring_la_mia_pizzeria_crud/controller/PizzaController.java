package it.springpizzeriacrud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model.Pizza;
import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    
    @Autowired
    private PizzaRepository repository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Pizza> result = repository.findAll();
        model.addAttribute("list", result);
        return "/index";
    }
    
}
