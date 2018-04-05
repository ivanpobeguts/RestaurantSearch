package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.model.Role;
import com.pobeguts.RestaurantSearch.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.pobeguts.RestaurantSearch.testData.UserTestData.*;
import static org.slf4j.LoggerFactory.getLogger;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private UserService service;

    private static final Logger log = getLogger(UserServiceTest.class);

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "AB", "new@gmail.com", "newPass", false, new Date(), Collections.EMPTY_SET, Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), newUser, ADMIN, USER1, USER2);
    }

    @Test
    public void update() throws Exception {
        User updated = USER1;
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN, USER2);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("user1@yandex.ru");
        assertMatch(user, USER1);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER1, USER2);
    }

    @Test
    public void voteForRestaurant() throws Exception{

    }

}