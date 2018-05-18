package com.pobeguts.RestaurantSearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pobeguts.RestaurantSearch.util.DateTimeUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote_history")
public class VoteHistory extends AbstractBaseEntity{

    @JsonFormat(timezone = "GMT+03:00")
    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime date_time = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rest_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public VoteHistory(){}

    public VoteHistory(VoteHistory u) {
        this(u.getId(), u.getDate_time(), u.getRestaurant(), u.getUser());
    }

    public VoteHistory(Integer id, LocalDateTime date_time, Restaurant restaurant, User user){
        super(id);
        this.date_time = date_time;
        setUser(user);
        setRestaurant(restaurant);
    }

    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteHistory)) return false;
        if (!super.equals(o)) return false;

        VoteHistory that = (VoteHistory) o;

        if (!getDate_time().equals(that.getDate_time())) return false;
        if (!getUser().equals(that.getUser())) return false;
        return getRestaurant().equals(that.getRestaurant());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getDate_time().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getRestaurant().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "VoteHistory{" +
                "date_time=" + date_time +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}

