package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.model.Role;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.repository.RestaurantRepository;
import com.pobeguts.RestaurantSearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final UserRepository userRepository;
    private  final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant add (Restaurant restaurant, int userId){
        User user = userRepository.findById(userId).orElse(null);
        if (user.getRoles().contains(Role.ROLE_ADMIN)){
            return restaurantRepository.save(restaurant);
        }
        return null;
    }

    public Restaurant get (int id) {
        return restaurantRepository.findById(id);
    }
}
