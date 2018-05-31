package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.TestUtil;
import com.pobeguts.RestaurantSearch.model.Menu;
import com.pobeguts.RestaurantSearch.service.MenuService;
import com.pobeguts.RestaurantSearch.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.pobeguts.RestaurantSearch.TestUtil.readFromJson;
import static com.pobeguts.RestaurantSearch.TestUtil.userHttpBasic;
import static com.pobeguts.RestaurantSearch.testData.MenuTestData.*;
import static com.pobeguts.RestaurantSearch.testData.RestaurantTestData.RESTAURANT_ID;
import static com.pobeguts.RestaurantSearch.testData.UserTestData.ADMIN;
import static com.pobeguts.RestaurantSearch.testData.UserTestData.USER1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuRestControllerTest extends AbstractControllerTest{
    private static final String REST_URL = MenuRestController.REST_URL + '/';

    @Autowired
    protected MenuService menuService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MENU1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MENU1));
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MENU1, MENU2, MENU3, MENU4, MENU5)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MENU1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(menuService.getAll(), MENU2, MENU3, MENU4, MENU5);
    }

    @Test
    public void testDeleteForbidden() throws Exception {
        mockMvc.perform(delete(REST_URL + MENU1_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testCreate() throws Exception {
        Menu expected = new Menu(null, "Новое меню");
        ResultActions action = mockMvc.perform(post(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isCreated());

        Menu returned = readFromJson(action, Menu.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(menuService.getAll(), MENU1, MENU2, MENU3, MENU4, MENU5, expected);
    }

    @Test
    public void testCreateForbidden() throws Exception {
        Menu expected = new Menu(null, "Новое меню1");
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))
                .with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden());
    }
}
