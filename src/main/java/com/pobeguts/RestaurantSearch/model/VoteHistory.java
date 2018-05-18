package com.pobeguts.RestaurantSearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pobeguts.RestaurantSearch.util.DateTimeUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_restaurants")
public class VoteHistory extends AbstractBaseEntity{

    @JsonFormat(timezone = "GMT+03:00")
    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime date_time = LocalDateTime.now();

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "rest_id")
    private int rest_id;

    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteHistory)) return false;

        VoteHistory that = (VoteHistory) o;

        if (getUser_id() != that.getUser_id()) return false;
        if (getRest_id() != that.getRest_id()) return false;
        return getDate_time().equals(that.getDate_time());
    }

    @Override
    public int hashCode() {
        int result = getDate_time().hashCode();
        result = 31 * result + getUser_id();
        result = 31 * result + getRest_id();
        return result;
    }

    @Override
    public String toString() {
        return "VoteHistory{" +
                "date_time=" + date_time +
                ", user_id=" + user_id +
                ", rest_id=" + rest_id +
                '}';
    }
}

