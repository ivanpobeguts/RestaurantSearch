package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.AuthorizedUser;
import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    static final String REST_URL = "/rest/users";

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public List<User> getUsers() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping("/{restId}/vote")
    public Set<Restaurant> vote(@PathVariable("restId") int restId) {
        return service.voteForRestaurant(AuthorizedUser.id(), restId);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView init(ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("msg", "Hello");
        return modelAndView;
    }

}
