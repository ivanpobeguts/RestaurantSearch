package com.pobeguts.RestaurantSearch.testData;

import com.pobeguts.RestaurantSearch.model.Role;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.web.json.JsonUtil;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static com.pobeguts.RestaurantSearch.model.AbstractBaseEntity.START_SEQ;
import static com.pobeguts.RestaurantSearch.web.json.JsonUtil.writeIgnoreProps;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class UserTestData {
    public static final int USER_ID = START_SEQ + 4;
    public static final int ADMIN_ID = START_SEQ + 6;

    public static final User USER1 = new User(USER_ID, "User1", "user1@yandex.ru", "password1", null, Role.ROLE_USER);
    public static final User USER2 = new User(USER_ID + 1, "User2", "user2@yandex.ru", "password2", null, Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", null, Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "password", "roles", "restaurant");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "password", "roles", "restaurant").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "registered", "password", "restaurant"));
    }

    public static ResultMatcher contentJson(User expected) {
        return content().json(writeIgnoreProps(expected, "registered", "password", "restaurant"));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
