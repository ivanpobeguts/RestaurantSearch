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

    public static final Menu MENU1 = new Menu(MENU1_ID, "{\"name\":\"свинная отбивная\",\"value\":25},{\"name\":\"картошка фри\",\"value\":15},{\"name\":\"яблочный сок\", \"value\":10}");
    public static final Menu MENU2 = new Menu(MENU1_ID + 1, "Меню2");
    public static final Menu MENU3 = new Menu(MENU1_ID + 2, "Меню3");
    public static final Menu MENU4 = new Menu(MENU1_ID + 3, "Меню4");
    public static final Menu MENU5 = new Menu(MENU1_ID + 4, "Меню5");

    public static Menu getCreated() {
        return new Menu(null, "Новое меню");
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
