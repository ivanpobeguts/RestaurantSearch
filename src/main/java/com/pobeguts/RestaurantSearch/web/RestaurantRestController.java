package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.AuthorizedUser;
import com.pobeguts.RestaurantSearch.model.Menu;
import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.service.RestaurantService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Restaurant addRestaurant(@RequestBody @Valid @Validated Restaurant restaurant) {
        return service.add(restaurant, AuthorizedUser.id());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void updateMenu(@RequestBody Menu menu, @PathVariable("id") int id) throws IOException {
        service.updateMenu(id, menu.toString(), AuthorizedUser.id());
    }

    @GetMapping(value = "/{id}")
    public Restaurant getRestaurant(@PathVariable("id") int id) {
        return service.get(id);
    }

    @GetMapping()
    public List<Restaurant> getRestaurantsJson() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id, AuthorizedUser.id());
    }

}

