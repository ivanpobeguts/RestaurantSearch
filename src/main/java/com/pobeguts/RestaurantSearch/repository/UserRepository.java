package com.pobeguts.RestaurantSearch.repository;

import com.pobeguts.RestaurantSearch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    @Transactional
    User save(User user);

    @Query("SELECT distinct u FROM User u LEFT JOIN FETCH u.restaurant WHERE u.email=:email")
    User getByEmail(@Param("email") String email);

    @Query("SELECT distinct u FROM User u LEFT JOIN FETCH u.restaurant WHERE u.id=:id")
    Optional<User> findById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT distinct u FROM User u LEFT JOIN FETCH u.restaurant order by u.name, u.email")
    List <User> getAll();

//    @Transactional
//    @Modifying
//    @Query(value = "INSERT into users_restaurants (user_id, rest_id) VALUES (:userId,:restId)", nativeQuery = true)
//    int vote(@Param("userId") int user_id, @Param("restId") int rest_id);
//
//    @Transactional
//    @Modifying
//    @Query(value = "DELETE FROM users_restaurants WHERE id=:id", nativeQuery = true)
//    int deleteVote(@Param("id") int id);

}
