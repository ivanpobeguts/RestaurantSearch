package com.pobeguts.model;

import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity{

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime registered;

    @Column(name = "menu", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String menu;

    @ManyToMany(mappedBy = "restaurants")
    private Set<User> users = new HashSet<>();

    public Restaurant() {
    }

    public Restaurant(LocalDateTime registered, String menu) {
        this(null, null, registered, menu);
    }

    public Restaurant(Integer id, String name, LocalDateTime registered, String menu) {
        super(id, name);
        this.registered = registered;
        this.menu = menu;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = CollectionUtils.isEmpty(users) ? Collections.emptySet() : users;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "registered=" + registered +
                ", id=" + id +
                ", menu='" + menu + '\'' +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
