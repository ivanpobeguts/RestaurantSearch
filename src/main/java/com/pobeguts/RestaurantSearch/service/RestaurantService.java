package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.repository.RestaurantRepository;
import com.pobeguts.RestaurantSearch.repository.UserRepository;
import com.pobeguts.RestaurantSearch.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.pobeguts.RestaurantSearch.util.ValidationUtil.checkNotFoundWithId;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class RestaurantService {

    private final UserRepository userRepository;
    private  final RestaurantRepository restaurantRepository;
    private static final Logger log = getLogger(RestaurantService.class);

    @Autowired
    public RestaurantService(UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant add(Restaurant restaurant, int userId){
        Assert.notNull(restaurant, "restaunant not found");
        return restaurantRepository.save(restaurant);
    }

    public Restaurant get (int id) {
        return restaurantRepository.findById(id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

}
