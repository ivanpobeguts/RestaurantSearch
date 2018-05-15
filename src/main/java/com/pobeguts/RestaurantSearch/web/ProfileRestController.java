package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.AuthorizedUser;
import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController {
    static final String REST_URL = "/rest/profile";

    private final UserService service;

    @Autowired
    public ProfileRestController(UserService service) {
        this.service = service;
    }

    @PostMapping("/{restId}")
    public Set<Restaurant> vote(@PathVariable("restId") int restId) {
        return service.voteForRestaurant(AuthorizedUser.id(), restId);
    }

    @GetMapping()
    public Set<Restaurant> get(){
        return service.getUserRestaurants(AuthorizedUser.id());
    }
}

