package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

//    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public boolean delete(int id) {
        return userRepository.delete(id) != 0;
    }

    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public List<User> getAll() {
        return userRepository.findAll(SORT_NAME_EMAIL);
    }

    public Set<Restaurant> voteForRestaurant(int userId, Restaurant restaurant){
        User user = get(userId);
        Set<Restaurant> restaurants = user.getRestaurants();
        restaurants.add(restaurant);
        return restaurants;
    }
}
