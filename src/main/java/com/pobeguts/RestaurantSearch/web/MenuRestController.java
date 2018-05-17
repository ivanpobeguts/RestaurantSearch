package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.model.Menu;
import com.pobeguts.RestaurantSearch.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    static final String REST_URL = "/rest/menu";

    private final MenuService menuService;

    @Autowired
    public MenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{restId}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void createNewMenuForRestaurant(@RequestBody Menu menu, @PathVariable("restId") int restId){
        menuService.create(menu, restId);
    }

    @GetMapping(value = "/{id}")
    public Menu getRestaurant(@PathVariable("id") int id) {
        return menuService.get(id);
    }

    @GetMapping()
    public List<Menu> getRestaurantsJson() {
        return menuService.getAll();
    }

}
