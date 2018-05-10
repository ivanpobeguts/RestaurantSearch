package com.pobeguts.RestaurantSearch.repository;

import com.pobeguts.RestaurantSearch.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Override
    @Transactional
    Restaurant save(@Param("restaurant") Restaurant restaurant);

    @Query("SELECT distinct r FROM Restaurant r LEFT JOIN FETCH r.users WHERE r.id=:id")
    Restaurant findById(@Param("id") int id);

    @Query("SELECT distinct r FROM Restaurant r LEFT JOIN FETCH r.users")
    List<Restaurant> getAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);
}
