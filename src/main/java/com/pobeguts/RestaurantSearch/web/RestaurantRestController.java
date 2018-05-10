package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.AuthorizedUser;
import com.pobeguts.RestaurantSearch.model.Menu;
import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.service.RestaurantService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    static final String REST_URL = "/rest/restaurants";
    private final RestaurantService service;
    private static final Logger log = getLogger(RestaurantRestController.class);

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return service.add(restaurant, AuthorizedUser.id());
    }

    @PatchMapping(value = "/{id}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Restaurant updateMenu(@RequestBody Menu menu, @PathVariable("id") int id) throws IOException {
        return service.updateMenu(id, menu.toString(), AuthorizedUser.id());
    }

    @GetMapping(value = "/{id}")
    public Restaurant getRestaurant(@PathVariable("id") int id) {
        return service.get(id);
    }

    @GetMapping()
    public List<Restaurant> getRestaurantsJson() {
        return service.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id, AuthorizedUser.id());
    }

//    @RequestMapping(value = "/count", method = RequestMethod.GET)
//    public int count(@RequestParam(value = "id") int id) {
//        return service.countVoices(id);
//    }

//    @RequestMapping(value = "/restaurants", method = RequestMethod.GET, produces = "text/html")
//    public ModelAndView getRestaurantsHtml() {
//        List<Restaurant> restaurants = service.getAll();
//        ModelAndView modelAndView = new ModelAndView("restaurants");
//        modelAndView.addObject("restaurants", restaurants);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/admin", method = RequestMethod.GET, produces = "text/html")
//    public ModelAndView adminPage() {
//        return new ModelAndView("admin");
//    }
}

