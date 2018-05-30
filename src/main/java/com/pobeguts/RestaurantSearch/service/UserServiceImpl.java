package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.AuthorizedUser;
import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.model.VoteHistory;
import com.pobeguts.RestaurantSearch.repository.RestaurantRepository;
import com.pobeguts.RestaurantSearch.repository.UserRepository;
import com.pobeguts.RestaurantSearch.repository.VoteHistoryRepository;
import com.pobeguts.RestaurantSearch.util.UserUtil;
import com.pobeguts.RestaurantSearch.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static com.pobeguts.RestaurantSearch.util.UserUtil.prepareToSave;
import static com.pobeguts.RestaurantSearch.util.ValidationUtil.checkNotFound;
import static com.pobeguts.RestaurantSearch.util.ValidationUtil.checkNotFoundWithId;
import static org.slf4j.LoggerFactory.getLogger;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");
    private static final Logger log = getLogger(UserDetailsService.class);

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    private final VoteHistoryRepository voteHistoryRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder, VoteHistoryRepository voteHistoryRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
        this.voteHistoryRepository = voteHistoryRepository;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(userRepository.save(user), user.getId());
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
//        return userRepository.delete(id) != 0;
        checkNotFoundWithId(userRepository.delete(id) != 0, id);
    }

    @Override
    public User get(int id) throws NotFoundException {
//        return userRepository.findById(id).orElse(null);
        return checkNotFoundWithId(userRepository.findById(id).orElse(null), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
//        return userRepository.getByEmail(email);
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void voteForRestaurant(int userId, int restId) {
        Restaurant restaurant = restaurantRepository.findById(restId);
        User user = get(userId);
        List<VoteHistory> voteHistoryList = voteHistoryRepository.getForDate(userId, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
        VoteHistory vote = voteHistoryList.get(0);
        if (vote != null) {
            UserUtil.checkTime();
        }
        user.setRestaurant(restaurant);
        update(user);
        VoteHistory newVote = new VoteHistory(null, LocalDateTime.now(), restaurant, user);
        voteHistoryRepository.save(newVote);
    }

    @Override
    public Restaurant getUserRestaurant(int userId) {
        User user = get(userId);
        checkNotFoundWithId(user, userId);
        return user.getRestaurant();
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
