package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.TestUtil;
import com.pobeguts.RestaurantSearch.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.pobeguts.RestaurantSearch.TestUtil.userHttpBasic;
import static com.pobeguts.RestaurantSearch.testData.RestaurantTestData.*;
import static com.pobeguts.RestaurantSearch.testData.UserTestData.USER1;
import static com.pobeguts.RestaurantSearch.web.ProfileRestController.REST_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    protected UserService userService;

    @Test
    public void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(USER1)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(singleContentJson(RESTAURANT1))
        );
    }

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testVote() throws Exception {
        mockMvc.perform(post(REST_URL + "/" + RESTAURANT_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk());
        TestUtil.print(
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(USER1)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(singleContentJson(RESTAURANT1))
        );
    }

}
