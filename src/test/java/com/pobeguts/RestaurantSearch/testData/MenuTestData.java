package com.pobeguts.RestaurantSearch.testData;

import com.pobeguts.RestaurantSearch.model.Menu;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static com.pobeguts.RestaurantSearch.model.AbstractBaseEntity.START_SEQ;
import static com.pobeguts.RestaurantSearch.web.json.JsonUtil.writeIgnoreProps;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class MenuTestData {
    public static final int MENU1_ID = START_SEQ + 7;

    public static final Menu MENU1 = new Menu(MENU1_ID, "{\"name\":\"chicken wings\",\"value\":25},{\"name\":\"fries\",\"value\":15},{\"name\":\"apple juice\", \"value\":10}");
    public static final Menu MENU2 = new Menu(MENU1_ID + 1, "{\"name\":\"pork\",\"value\":30},{\"name\":\"fries\",\"value\":15},{\"name\":\"orange juice\", \"value\":15}");
    public static final Menu MENU3 = new Menu(MENU1_ID + 2, "{\"name\":\"pizza carbonara\",\"value\":30},{\"name\":\"black tea\", \"value\":7}");
    public static final Menu MENU4 = new Menu(MENU1_ID + 3, "{\"name\":\"salmon\",\"value\":25},{\"name\":\"rice\",\"value\":15},{\"name\":\"white wine\", \"value\":14}");
    public static final Menu MENU5 = new Menu(MENU1_ID + 4, "{\"name\":\"scramble\",\"value\":15},{\"name\":\"pancake\",\"value\":10},{\"name\":\"cappuccino\", \"value\":10}");

    public static Menu getCreated() {
        return new Menu(null, "New menu");
    }

    public static Menu getUpdated() {
        return new Menu(MENU1_ID, MENU1.getMenu());
    }

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "registered");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "registered").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Menu... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "restaurant", "registered"));
    }

    public static ResultMatcher contentJson(Menu expected) {
        return content().json(writeIgnoreProps(expected, "restaurant", "registered"));
    }
}
