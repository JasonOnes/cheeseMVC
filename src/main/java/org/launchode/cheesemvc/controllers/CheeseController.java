package org.launchode.cheesemvc.controllers;


import org.launchode.cheesemvc.models.Cheese;
import org.launchode.cheesemvc.models.CheeseData;
import org.launchode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@Controller
@RequestMapping("cheese")
public class CheeseController {

    @RequestMapping(value = "")

    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Sweet Cheesus!");
        ArrayList<Cheese> cheeses = new ArrayList<>();
        cheeses = CheeseData.getAll(); //note checking if cheese objects number the ids (they don't)
        System.out.println(cheeses.size());
        return "cheese/index"; //base prefix + template name
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseFlavor) {
//        Cheese newCheese = new Cheese(cheeseName, cheeseFlavor);
//        CheeseData.add(newCheese);
//note above refactored using model binding
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }

        CheeseData.add(newCheese);
        return "redirect:";

    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayCheeseDelete(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Delete");
        return "cheese/delete";

    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processCheeseDelete(@RequestParam int[] cheeseNoMo) {

        for (int cheeseId : cheeseNoMo) {
            CheeseData.delete(cheeseId);
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese freshCheese = CheeseData.getById(cheeseId);
        model.addAttribute("cheesey", freshCheese);
        model.addAttribute("cheeseTypes", CheeseType.values());

        return "cheese/edit";

    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(Model model,  @ModelAttribute @Valid Cheese freshCheese, Errors errors,
                                  @RequestParam String name, String description, CheeseType type) {

        if (errors.hasErrors()){
            model.addAttribute("title","edit cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/edit";
        }

        int oldId = freshCheese.getCheeseId();
        CheeseData.delete(oldId);//note necessary so that there aren't two objects with same id look up way to make @unique?
        freshCheese.setName(name);
        freshCheese.setDescription(description);
        freshCheese.setType(type);
        model.addAttribute("cheesey", freshCheese);
        CheeseData.add(freshCheese);

        return "redirect:/cheese"; //note redirect without /path go all the way to root url


    }


}

