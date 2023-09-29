package org.learning.java.springblogricette.controller;

import org.learning.java.springblogricette.model.Recipe;
import org.learning.java.springblogricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public String index(Model model){
        List<Recipe> recipeList = recipeRepository.findAll();
        model.addAttribute("recipesObj", recipeList);
        return "recipes/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model ){
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipeDB = recipeOptional.get();
            model.addAttribute("recipesObj", recipeOptional.get());
            return "recipes/detail";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with id " + id + " not found");
        }

    }

    //-------------create---------------------

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("recipesObj", new Recipe());
        return "recipes/create";
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute("recipesObj")Recipe recipeForm,
    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "recipes/create";
        }
        recipeRepository.save(recipeForm);
        return "redirect:/";
    }

    //----------------------Edit--------------------
    @GetMapping("/edit/recipe/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()){
            model.addAttribute("recipesObj", recipeOptional.get());
            return "recipes/edit";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with id " + id + " not found");
        }
    }

    @PostMapping("/edit/recipe/{id}")
    public String doEdit (@PathVariable Integer id, @ModelAttribute("recipesObj") Recipe recipeForm,
                          BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/recipes/edit";
        }
        recipeRepository.save(recipeForm);
        return "redirect:/";
    }

    //-------------Delete---------------------
    @PostMapping("delete/recipe/{id}")
    public String delete(@PathVariable Integer id){
        recipeRepository.deleteById(id);
        return "redirect:/";
    }
}
