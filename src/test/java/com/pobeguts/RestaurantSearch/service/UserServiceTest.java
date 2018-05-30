package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.model.Role;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.pobeguts.RestaurantSearch.testData.UserTestData.*;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
public class UserServiceTest extends AbstractServiceTest{

    @Autowired
    private UserService userService;

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false, new Date(), null, Collections.singleton(Role.ROLE_USER));
        User created = userService.create(newUser);
        newUser.setId(created.getId());
        assertMatch(userService.getAll(), ADMIN, newUser, USER1, USER2);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        userService.create(new User(null, "Duplicate", "user1@yandex.ru", "newPass", null, Role.ROLE_USER));
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        userService.update(updated);
        assertMatch(userService.get(USER_ID), updated);
    }

    @Test
    public void delete() throws Exception {
        userService.delete(USER_ID);
        assertMatch(userService.getAll(), ADMIN, USER2);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        userService.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = userService.get(USER_ID);
        assertMatch(user,  USER1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        userService.get(1);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = userService.getAll();
        assertMatch(all, ADMIN, USER1, USER2);
    }

}