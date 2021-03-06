package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.model.Menu;
import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.repository.MenuRepository;
import com.pobeguts.RestaurantSearch.repository.RestaurantRepository;
import com.pobeguts.RestaurantSearch.util.exception.NotFoundException;
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
        Restaurant found = checkNotFoundWithId(restaurantRepository.findById(restId), restId);
        menu.setRestaurant(found);
        return menuRepository.save(menu);
    }

    public Menu get (int id) throws NotFoundException {
        return checkNotFoundWithId(menuRepository.findById(id), id);
    }

    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id) != 0, id);
    }
}
