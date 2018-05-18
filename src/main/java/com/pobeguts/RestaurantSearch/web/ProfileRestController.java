package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.AuthorizedUser;
import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public void vote(@PathVariable("restId") int restId) {
        service.voteForRestaurant(AuthorizedUser.id(), restId);
    }

    @GetMapping()
    public Restaurant get(){
        return service.getUserRestaurant(AuthorizedUser.id());
    }
}

