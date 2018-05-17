package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.model.Menu;
import com.pobeguts.RestaurantSearch.repository.MenuRepository;
import com.pobeguts.RestaurantSearch.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.pobeguts.RestaurantSearch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private  final RestaurantRepository restaurantRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Menu create(Menu menu, int restId) {
        Assert.notNull(menu, "menu must not be null");
        menu.setRestaurant(restaurantRepository.findById(restId));
        return menuRepository.save(menu);
    }

    public void update(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(menuRepository.save(menu), menu.getId());
    }

    public Menu get (int id) {
        return menuRepository.findById(id);
    }

    public List<Menu> getAll() {
        return menuRepository.getAll();
    }
}
