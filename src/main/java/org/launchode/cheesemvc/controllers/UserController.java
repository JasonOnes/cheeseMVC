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
import java.util.Date;


@Controller
@RequestMapping("user")
public class UserController {
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

//        String password = newUser.getPassword();
        String username = newUser.username;
        newUser.checkPassword();
        if (errors.hasErrors()) {
            model.addAttribute("title", "Try again");
            model.addAttribute(newUser);
            return "user/add";
//        } else if (!password.equals(passwordVerify)) {
//            String msg = "Passwords must match!";//note look for way to pass this into Thymeleaf better
//            model.addAttribute("msg2", msg);
//            model.addAttribute("username", newUser.username);
//            return "user/add";
        }

         else if (!UserValidate.nameIsAlpha(username)) {
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

    @RequestMapping(value = "detes/{userId}", method = RequestMethod.GET)//note cannot get user updated with git userId
    public String displayDetails(HttpSession session, Model model, @PathVariable int userId) {
        //User user = session.getAttribute("user", User newUser);
        //session.removeAttribute("user");//note needed to reset/update session with variable of same name
//        System.out.println(session.getAttribute("user"));
//        System.out.println(session.getAttribute("user"));

        //note not liking the session attrib pain
//        session.setAttribute("user", someUser);
//        System.out.println(session.getAttribute("user"));
//        System.out.println(someUser.username);
//        System.out.println("************************************");

        User someUser = UserData.getById(userId);
        model.addAttribute("user", someUser);

        model.addAttribute("title", "user details");
        return "user/detes";

    }

    @RequestMapping(value = "detes/{userId}", method = RequestMethod.POST)
    public String showDetails(HttpSession session, Model model, @PathVariable int userId) {
        //note maybe need a post route with the userId? since GET isn't workingth
        User someUser = UserData.getById(userId);
        model.addAttribute("user", someUser);

        model.addAttribute("title", "user details");
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
            UserData.delete(UserData.getById(luserId));

        }
        return "redirect:";
    }
}

