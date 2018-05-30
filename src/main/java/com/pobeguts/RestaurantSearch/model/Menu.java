package com.pobeguts.RestaurantSearch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity{

    @JsonFormat(timezone = "GMT+03:00")
    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @Column(name = "menu", nullable = false)
    @NotBlank
    @Size(min = 2, max = 500)
    private String menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
//    @NotNull
    @JsonBackReference
    private Restaurant restaurant;

    public Menu(){}

    public Menu (Integer id, String menu){
        this(id, new Date(), menu, null);
    }

    public Menu (Integer id, Date registered, String menu, Restaurant restaurant){
        this.id = id;
        this.registered = registered;
        this.menu = menu;
        this.restaurant = restaurant;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        if (!super.equals(o)) return false;

        Menu menu1 = (Menu) o;

        if (!getRegistered().equals(menu1.getRegistered())) return false;
        if (!getMenu().equals(menu1.getMenu())) return false;
        return getRestaurant().equals(menu1.getRestaurant());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getRegistered().hashCode();
        result = 31 * result + getMenu().hashCode();
        result = 31 * result + getRestaurant().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "registered=" + registered +
                ", menu='" + menu + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }


}
