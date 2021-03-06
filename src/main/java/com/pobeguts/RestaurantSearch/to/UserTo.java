package com.pobeguts.RestaurantSearch.to;

import com.pobeguts.RestaurantSearch.model.Restaurant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
    private Restaurant restaurant;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password, Restaurant restaurant) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.restaurant = restaurant;
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

    public void setRestaurants(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", caloriesPerDay='" + restaurant + '\'' +
                '}';
    }
}
