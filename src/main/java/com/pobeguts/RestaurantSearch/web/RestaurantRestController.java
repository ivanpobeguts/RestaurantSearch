package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.AuthorizedUser;
import com.pobeguts.RestaurantSearch.model.Menu;
import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.service.RestaurantService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
        return service.add(restaurant, AuthorizedUser.id());
    }

    @RequestMapping(value = "/updateMenu", method = RequestMethod.PATCH,
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Restaurant updateMenu(@RequestBody Menu menu, @RequestParam(value = "id") int id) throws IOException {
//        JsonNode rootNode = new ObjectMapper().readTree(new StringReader(menu));
//        String menuFromRequest = rootNode.get("menu").asText();
        return service.updateMenu(id, menu.toString(), AuthorizedUser.id());
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public Restaurant getRestaurant(@RequestParam(value = "id") int id) {
        return service.get(id);
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET, produces = "application/json")
    public List<Restaurant> getRestaurantsJson() {
        return service.getAll();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(@RequestParam(value = "id") int id) {
        return service.countVoices(id);
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView getRestaurantsHtml() {
        List<Restaurant> restaurants = service.getAll();
        ModelAndView modelAndView = new ModelAndView("restaurants");
        modelAndView.addObject("restaurants", restaurants);
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView adminPage() {
        return new ModelAndView("admin");
    }
}

