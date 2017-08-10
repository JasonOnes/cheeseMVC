package org.launchode.cheesemvc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;


@Controller
@RequestMapping("cheese")
public class CheeseController {

    HashMap<String, String> cheeses = new HashMap<>();

    @RequestMapping(value="")

    public String index(Model model){


        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Sweet Cheesus!");
        return "cheese/index"; //base prefix + template name
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute("description", "Describe the Cheese");
        return "cheese/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String  processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseFlavor) {

        //cheeses.add(cheeseName);
        cheeses.put(cheeseName, cheeseFlavor);
        return "redirect:"; //by leaving :blank redirects to base handler

    }

}
