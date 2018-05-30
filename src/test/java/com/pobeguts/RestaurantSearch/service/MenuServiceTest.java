package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.model.Menu;
import com.pobeguts.RestaurantSearch.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static com.pobeguts.RestaurantSearch.testData.MenuTestData.*;
import static com.pobeguts.RestaurantSearch.testData.RestaurantTestData.RESTAURANT_ID;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
public class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuService service;

    @Test
    public void create() throws Exception {
        Menu created = getCreated();
        service.create(created, RESTAURANT_ID);
        assertMatch(service.getAll(), MENU1, MENU2, MENU3, MENU4, MENU5, created);
    }

    @Test
    public void createNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        Menu created = getCreated();
        service.create(created, 100);
    }

    @Test
    public void get() throws Exception {
        Menu actual = service.get(MENU1_ID);
        assertMatch(actual, MENU1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(1);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(), MENU1, MENU2, MENU3, MENU4, MENU5);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MENU1_ID);
        assertMatch(service.getAll(), MENU2, MENU3, MENU4, MENU5);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(1);
    }

}