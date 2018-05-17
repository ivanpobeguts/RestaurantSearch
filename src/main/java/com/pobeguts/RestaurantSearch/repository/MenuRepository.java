package com.pobeguts.RestaurantSearch.repository;

import com.pobeguts.RestaurantSearch.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Override
    @Transactional
    Menu save(@Param("restaurant") Menu menu);

    @Query("SELECT distinct m FROM Menu m LEFT JOIN FETCH m.restaurant WHERE m.id=:id")
    Menu findById(@Param("id") int id);

    @Query("SELECT distinct m FROM Menu m LEFT JOIN FETCH m.restaurant")
    List<Menu> getAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);
}
