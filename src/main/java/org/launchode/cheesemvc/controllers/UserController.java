package org.launchode.cheesemvc.controllers;


import org.launchode.cheesemvc.models.User;
import org.launchode.cheesemvc.models.UserData;
import org.launchode.cheesemvc.models.UserValidate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("user", "user");
        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title", "New User");
        return "user/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    //note use model binding such that
    //note where to put String msg;

    public String add(HttpSession session, @ModelAttribute @Valid User newUser, Errors errors, Model model,
                      String passwordVerify) {

//        newUser.setUsername(username);
//        newUser.setPassword(password);
        String password = newUser.getPassword();
        //System.out.println(newUser.username);
        String username = newUser.username;
        //String passwordVerify;// = user.getPasswordVerify();

        if (errors.hasErrors()) {
            model.addAttribute(new User());
            return "user/add";
        }
        else if (!password.equals(passwordVerify)) {
            String msg = "whatever";// = "Passwords must match!";
            model.addAttribute("msg2", msg);
            model.addAttribute("username", newUser.username);

            return "user/add";
        }
        else if (!UserValidate.nameIsAlpha(username)) {
            String msg = "Username can only contain letters.";
            model.addAttribute("msg1", msg);
            return "user/add";
        }
        else {
            //System.out.println(newUser.username);
            //username = newUser.username;
            //int id = newUser.getUserId();
            //model.addAttribute("newUser", UserData.getById(id));
            //model.addAttribute("newUser", newUser);
            model.addAttribute("users", UserData.getAll());
            model.addAttribute("user", newUser);
//            model.addAttribute("username", newUser.getUsername());
            //model.addAttribute("username", newUser.username);
            UserData.add(newUser);
            System.out.println(newUser.username);
            System.out.println(newUser.getUserId());
            System.out.println(username);
            System.out.println(newUser.getEmail());


            session.setAttribute("username", newUser.username);
           return "redirect:";

        }

    }
    //note why in the hell can't I use annotations here, what is so damned different HERE
//    @RequestMapping(value = "/remove", method = RequestMethod.GET) {
//
//        public String getUserToRemove(@ModelAttribute User loserUser, )
//    }


}
