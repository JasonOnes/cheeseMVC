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


@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping(value = "")
    public String index(Model model) {
        //model.addAttribute("username", "Nobody");
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
            model.addAttribute("title", "Try again");
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
//            //System.out.println(newUser.username);
//            //username = newUser.username;
//            //int id = newUser.getUserId();
//            //model.addAttribute("newUser", UserData.getById(id));
//            //model.addAttribute("newUser", newUser);
//            model.addAttribute("users", UserData.getAll());
//            model.addAttribute("user", newUser);
////            model.addAttribute("username", newUser.getUsername());
//            //model.addAttribute("username", newUser.username);
//
//            System.out.println(newUser.username);
//            System.out.println(newUser.getUserId());
//            System.out.println(username);
//            System.out.println(newUser.getEmail());
//            newUser.setUsername(username);
//            model.addAttribute("users", UserData.getAll());
//            model.addAttribute("user", newUser);
//            model.addAttribute("username", "username");
//            model.addAttribute(newUser);
//            //session.setAttribute("username", newUser.username);

            UserData.add(newUser);
            model.addAttribute("username", newUser.username);
            model.addAttribute("user", "newUser");
            //session.setAttribute("username", newUser.username);
            session.setAttribute("user", newUser);
            //model.addAttribute("newUser");
           return "redirect:";

        }

    }

    @RequestMapping(value = "detes/{userId}", method = RequestMethod.GET)//note cannot get user updated with userId
    public String displayDetails(HttpSession session, Model model, @PathVariable int userId) {
        //User user = session.getAttribute("user", User newUser);
        //session.removeAttribute("user", user);//note needed to reset/update session with variable of same name
        System.out.println(session.getAttribute("user"));
        session.removeAttribute("user");
        System.out.println(session.getAttribute("user"));
        User someUser = UserData.getById(userId);

        //session.setAttribute("user", someUser);
        System.out.println(session.getAttribute("user"));
        System.out.println(someUser.username);
        System.out.println("************************************");

        model.addAttribute("user", someUser);

        model.addAttribute("title", "user details");
        return "user/detes";

    }

    @RequestMapping(value = "detes/{userId}", method = RequestMethod.POST)
    public String showDetails(HttpSession session, Model model, @PathVariable int userId) {
        //User user = session.getAttribute("user", User newUser);
        //session.removeAttribute("user", user);//note needed to reset/update session with variable of same name
        System.out.println(session.getAttribute("user"));
        session.removeAttribute("user");
        System.out.println(session.getAttribute("user"));
        User someUser = UserData.getById(userId);

        //session.setAttribute("user", someUser);
        System.out.println(session.getAttribute("user"));
        System.out.println(someUser.username);
        System.out.println("************************************");

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

        }return "redirect:";
    }

    //note why in the hell can't I use annotations here, what is so damned different HERE
//    @RequestMapping(value = "/remove", method = RequestMethod.GET) {
//
//        public String getUserToRemove(@ModelAttribute User loserUser, )
//    }


}
