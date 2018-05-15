package com.pobeguts.RestaurantSearch.to;

import com.google.common.collect.ImmutableSet;
import com.pobeguts.RestaurantSearch.model.Restaurant;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UserTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    //@Size(min = 5, max = 32, message = "length must between 5 and 32 characters")
    @Size(min = 5, max = 32)
    private String password;

//    @Range(min = 10, max = 10000)
    @NotNull
    private Set<Restaurant> restaurants = new HashSet<>();

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password, Set<Restaurant> restaurants) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.restaurants = restaurants;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = CollectionUtils.isEmpty(restaurants) ? Collections.emptySet() : ImmutableSet.copyOf(restaurants);
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", caloriesPerDay='" + restaurants + '\'' +
                '}';
    }
}
