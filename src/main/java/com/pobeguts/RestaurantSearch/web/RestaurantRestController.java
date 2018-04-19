package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.service.RestaurantService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class RestaurantRestController {

    private final RestaurantService service;
    private static final Logger log = getLogger(RestaurantRestController.class);

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @RequestMapping(value = "/add_restaurant", method = RequestMethod.POST,
            consumes = "*/*;charset=UTF-8")
    @ResponseBody
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return service.add(restaurant, 100002);
    }

    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST,
            consumes = "*/*;charset=UTF-8")
    @ResponseBody
    public Restaurant updateMenu(@RequestBody String menu, @RequestParam(value = "id") int id){
        return service.updateMenu(id, menu, 100002);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public Restaurant getRestaurant(@RequestParam(value = "id") int id) {
        return service.get(id);
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public List<Restaurant> getRestaurants() {
        return service.getAll();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(@RequestParam(value = "id") int id) {
        return service.countVoices(id);
    }
}

