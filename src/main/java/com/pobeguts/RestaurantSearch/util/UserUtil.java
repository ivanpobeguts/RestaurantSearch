package com.pobeguts.RestaurantSearch.util;

import com.pobeguts.RestaurantSearch.model.Role;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.to.UserTo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UserUtil {

    private static final int LIMIT_HOUR = 11;
    private static final int LIMIT_MINUTES = 0;

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), null, Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(), null);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        user.setRestaurant(userTo.getRestaurant());
        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static void checkTime(){
        LocalDateTime now = LocalDateTime.now();
        LocalTime base = LocalTime.of(LIMIT_HOUR, LIMIT_MINUTES);
        Duration d = Duration.between(now.toLocalTime(), base);
        if (d.toNanos() < 0){
            throw new UnsupportedOperationException("You can't vote after " + LocalTime.of(LIMIT_HOUR, LIMIT_MINUTES));
        }
    }
}
