package com.pobeguts.RestaurantSearch.web;


import com.pobeguts.RestaurantSearch.AuthorizedUser;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private final UserService service;

    @Autowired
    public MainController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST, produces = "text/html")
    public String login(Model model, @ModelAttribute("email") String email, @ModelAttribute("password") String password) {
        User user = service.getByEmail(email);
        if (user.getPassword().equals(password)){
            AuthorizedUser.setId(user.getId());
            model.addAttribute("user", user);
            return "welcome";
        }
        else {
            model.addAttribute("message", "Invalid password");
            return "login";
        }
    }
}
