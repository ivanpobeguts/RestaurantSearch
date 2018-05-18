package com.pobeguts.RestaurantSearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.ImmutableSet;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;


@Entity
@Table(name = "restaurants")
//@JsonIgnoreProperties({"password", "enabled", "new"})
public class Restaurant extends AbstractNamedEntity{

//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+03:00")
    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant", cascade = CascadeType.ALL)
    @OrderBy("registered DESC")
//    @JsonBackReference
//    @JsonManagedReference
    private List<Menu> menu;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "restaurants")
//    @JsonBackReference
//    @JsonManagedReference
    private Set<User> users = new HashSet<>();

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, List<Menu> menu, Set<User> users) {
        this(id, name, new Date(), menu, users);
    }

    public Restaurant(Integer id, String name, Date registered, List<Menu> menu, Collection<User> users) {
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

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
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
