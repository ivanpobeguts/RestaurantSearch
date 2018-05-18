package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.AuthorizedUser;
import com.pobeguts.RestaurantSearch.model.Restaurant;
import com.pobeguts.RestaurantSearch.model.User;
import com.pobeguts.RestaurantSearch.model.VoteHistory;
import com.pobeguts.RestaurantSearch.repository.RestaurantRepository;
import com.pobeguts.RestaurantSearch.repository.UserRepository;
import com.pobeguts.RestaurantSearch.repository.VoteHistoryRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static com.pobeguts.RestaurantSearch.util.UserUtil.prepareToSave;
import static com.pobeguts.RestaurantSearch.util.ValidationUtil.checkNotFoundWithId;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserService implements UserDetailsService {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");
    private static final Logger log = getLogger(UserDetailsService.class);

    private final UserRepository userRepository;
    private  final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    private final VoteHistoryRepository voteHistoryRepository;

    @Autowired
    public UserService(UserRepository userRepository, RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder, VoteHistoryRepository voteHistoryRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
        this.voteHistoryRepository = voteHistoryRepository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(userRepository.save(prepareToSave(user, passwordEncoder)), user.getId());
    }

    public boolean delete(int id) {
        return userRepository.delete(id) != 0;
    }

    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void voteForRestaurant(int userId, int restId){
        List<VoteHistory> voteHistoryList = voteHistoryRepository.getForDate(userId, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
        VoteHistory vote = voteHistoryList.get(0);
        if (voteHistoryList == null){
            checkNotFoundWithId(userRepository.vote(userId, restId), restId);
        }
        else {
            LocalDateTime date = vote.getDate_time();
            LocalDateTime now = LocalDateTime.now();
//        if (ldt.toLocalDate().isEqual(now.toLocalDate())){
            LocalTime base = LocalTime.of(20,0);
            Duration d = Duration.between(now.toLocalTime(), base);
            log.info("********** " + d.toNanos() + " **************");
            if (d.toNanos() < 0){
                throw new UnsupportedOperationException("You can't vote after 11");
            }
            vote.setRest_id(restId);
            userRepository.deleteVote(vote.getId());
//            voteHistoryRepository.delete(voteHistoryList.get(0).getId());
//            checkNotFoundWithId(userRepository.vote(userId, restId), restId);
//            voteHistoryRepository.save(vote);
        }
    }

    public Set<Restaurant> getUserRestaurants(int userId){
        User user = get(userId);
        checkNotFoundWithId(user, userId);
        return user.getRestaurants();
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
