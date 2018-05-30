package com.pobeguts.RestaurantSearch.testData;

import com.pobeguts.RestaurantSearch.model.Restaurant;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static com.pobeguts.RestaurantSearch.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static com.pobeguts.RestaurantSearch.web.json.JsonUtil.writeIgnoreProps;

public class RestaurantTestData {
    public static final int RESTAURANT_ID = START_SEQ;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID, "Ресторан1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID + 1, "Ресторан2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID + 2, "Ресторан3");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT_ID + 3, "Ресторан4");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menu", "users", "registered");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menu", "users", "registered").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "menu", "users", "registered"));
    }

    public static ResultMatcher singleContentJson(Restaurant expected) {
        return content().json(writeIgnoreProps(expected, "menu", "users", "registered"));
    }

}
