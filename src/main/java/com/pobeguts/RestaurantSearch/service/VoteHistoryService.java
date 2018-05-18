package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.repository.RestaurantRepository;
import com.pobeguts.RestaurantSearch.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class VoteHistoryService {
    private final UserRepository userRepository;
    private  final RestaurantRepository restaurantRepository;
    private static final Logger log = getLogger(RestaurantService.class);

    @Autowired
    public VoteHistoryService(UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }
}
