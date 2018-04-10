package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class UserRestController {

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value="/userlist")
    public List<User> getUsers() {
        return service.getAll();
    }

    @RequestMapping("/user")
    public User getUser(@RequestParam(value = "id") int id) {
        return service.get(id);
    }

    @RequestMapping("/vote")
    public Set<Restaurant> vote(@RequestParam(value = "id") int id, @RequestParam(value = "restId") int restId) {
        return service.voteForRestaurant(id, restId);
    }
}
