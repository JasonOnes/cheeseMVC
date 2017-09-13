package org.launchode.cheesemvc.controllers;


import org.launchode.cheesemvc.models.User;
import org.launchode.cheesemvc.models.UserData;
import org.launchode.cheesemvc.models.UserValidate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

import static org.launchode.cheesemvc.models.UserData.getById;


@Controller
@RequestMapping("user")
public class UserController {

    //private UserData userData = UserData.getInstance();
    //ArrayList<User> users = userData.getAll();

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title", "New User");
        return "user/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "New User!");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpSession session, @ModelAttribute @Valid User newUser, Errors errors, Model model,
                      String passwordVerify) {

        String username = newUser.username;
        newUser.checkPassword();
        if (errors.hasErrors()) {
            model.addAttribute("title", "Try again");
            model.addAttribute(newUser);
            return "user/add";

        } else if (!UserValidate.nameIsAlpha(username)) {
            String msg = "Username can only contain letters.";
            model.addAttribute("msg1", msg);
            return "user/add";
        } else {

            Date now = new Date();
            newUser.setDateAdded(now);
            UserData.add(newUser);
            model.addAttribute("user", newUser);
            //session.setAttribute("user", newUser);//note if going session route to display username
            return "redirect:";

        }

    }
    @RequestMapping(value="detes/{userId}", method = RequestMethod.GET)
    public String showDetes(Model model, @PathVariable int userId) {
        User someUser = UserData.getById(userId);
        model.addAttribute("title", "user details");
        model.addAttribute("user", someUser);
        return "user/detes";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayUserToRemove(Model model) {
        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title", "Sayonara luser!");
        return "user/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processUserRemoval(@RequestParam int[] userIds) {
        for (int luserId : userIds) {
            UserData.delete(getById(luserId));

        }
        return "redirect:";
    }
}

