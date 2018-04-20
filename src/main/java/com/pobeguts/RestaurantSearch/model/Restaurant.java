package com.pobeguts.RestaurantSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ImmutableSet;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Table(name = "restaurants")
@JsonIgnoreProperties({"password", "enabled", "new"})
public class Restaurant extends AbstractBaseEntity{

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
//    @NotNull
    private Date registered;

    @Column(name = "menu", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String menu;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "restaurants")
//    @JsonBackReference
//    @JsonManagedReference
    private Set<User> users = new HashSet<>();

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String menu, Set<User> users) {
        this(id, name, new Date(), menu, users);
    }

    public Restaurant(Integer id, String name, Date registered, String menu, Collection<User> users) {
        super(id, name);
        this.registered = registered;
        this.menu = menu;
        this.setUsers(users);
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

//    @JsonBackReference
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = CollectionUtils.isEmpty(users) ? Collections.emptySet() : ImmutableSet.copyOf(users);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                '}';
    }
}
