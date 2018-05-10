package com.pobeguts.RestaurantSearch.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class Menu {

    private ArrayList<JsonNode> dishes;

    public ArrayList<JsonNode> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<JsonNode> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return dishes.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        return getDishes() != null ? getDishes().equals(menu.getDishes()) : menu.getDishes() == null;
    }

    @Override
    public int hashCode() {
        return getDishes() != null ? getDishes().hashCode() : 0;
    }
}
