package com.pobeguts.repository;

import com.pobeguts.model.Restaurant;

import java.util.List;

public class JpaRestaurantRepository implements RestaurantRepository{
    @Override
    public Restaurant save(Restaurant rest) {
        return null;
    }

    @Override
    public boolean delete(Restaurant rest) {
        return false;
    }

    @Override
    public Restaurant get(int id) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
