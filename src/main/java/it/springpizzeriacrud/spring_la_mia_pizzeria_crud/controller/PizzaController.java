package it.springpizzeriacrud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model.Pizza;
import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    
    @Autowired
    private PizzaRepository repository;

    @GetMapping("/index")
    public String index(@RequestParam(name="keyword", required=false) String keyword, Model model) {
        List<Pizza> result = null;
        if(keyword == null || keyword.isBlank()) {
            result = repository.findAll();
        } else {
            result = repository.findByNomeContainingIgnoreCase(keyword);
        }
        model.addAttribute("list", result);
        return "/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional<Pizza> optionalPizza = repository.findById(id);
        if (optionalPizza.isPresent()) {
            model.addAttribute("pizza", optionalPizza.get());
            model.addAttribute("empty", false);
        } else {
            model.addAttribute("empty", true);
        }
        return "/show";
    }
    
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/create";
    }

    @PostMapping("/create")
    public String save(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Optional<Pizza> optPizza = repository.findByNome(formPizza.getNome());
        if(optPizza.isPresent()){
            bindingResult.addError(new FieldError("pizza", "nome", "Nome già presente"));
        }
        if(bindingResult.hasErrors()) {
            return "/create";
        }
        repository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza created successfully");
        return "redirect:/pizzas/index";
    }
    

}
