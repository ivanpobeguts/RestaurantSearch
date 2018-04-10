package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.service.RestaurantService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class RestaurantRestController {
    private final RestaurantService service;
    private static final Logger log = getLogger(RestaurantRestController.class);

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @RequestMapping(value = "/add_restaurant", method = RequestMethod.POST, consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
    public void addRestaurant(@RequestBody String restaurant) {
        log.info("@@@@@@@@@@@@@@@@@@@@@ Restaurant saved: ", restaurant);
//        return service.add(restaurant, 100002);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public Restaurant getRestaurant(@RequestParam(value = "id") int id) {
        return service.get(id);
    }
}

