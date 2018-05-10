package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.model.Role;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.repository.RestaurantRepository;
import com.pobeguts.RestaurantSearch.repository.UserRepository;
import com.pobeguts.RestaurantSearch.util.NotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User user = userRepository.findById(userId).orElse(null);
        if (user.getRoles().contains(Role.ROLE_ADMIN)){
            return restaurantRepository.save(restaurant);
        }
        return null;
    }

    public Restaurant updateMenu (int restId, String menu, int userId){
        Restaurant restaurant = restaurantRepository.findById(restId);
        if (isAdmin(userId)){
            restaurant.setMenu(menu);
            return restaurantRepository.save(restaurant);
        }
        return null;
    }

    public Restaurant get (int id) {
        return restaurantRepository.findById(id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public int countVoices(int id){
        return get(id).getUsers().size();
    }

    public void delete(int id, int userId) throws NotFoundException {
        if (isAdmin(userId)) {
            checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
        }
        else {
            throw new NotFoundException("User must be admin!");
        }
    }

    public boolean isAdmin(int userId){
        User user = userRepository.findById(userId).orElse(null);
        return user.getRoles().contains(Role.ROLE_ADMIN);
    }
}
