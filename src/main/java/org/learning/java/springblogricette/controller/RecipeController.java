package org.learning.java.springblogricette.controller;

import org.learning.java.springblogricette.model.Recipe;
import org.learning.java.springblogricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public String index(Model model){
        //List<Recipe> recipeList = recipeRepository.findAll();
        //model.addAttribute("recipesObj", recipeList);
        return "recipes/index";
    }
}
