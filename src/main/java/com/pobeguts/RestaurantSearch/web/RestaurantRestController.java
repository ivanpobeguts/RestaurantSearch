package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

public class RestaurantRestController {
    private final RestaurantService service;

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

//    @RequestMapping("/vote")
//    public Restaurant addRestaurant(@RequestParam(value = "id") int id, @RequestParam(value = "restId") int restId) {
//        return service.add()
//    }
}

