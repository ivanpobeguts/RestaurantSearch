package com.pobeguts.repository;

import com.pobeguts.model.Restaurant;

import java.util.List;

/**
 * Created by Pobeguts on 30.03.2018.
 */
public interface RestaurantRepository {

    Restaurant save(Restaurant rest);

    boolean delete(Restaurant rest);

    Restaurant get(int id);

    List<Restaurant> getAll();
}
