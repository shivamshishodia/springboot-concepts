package com.shishodia.microservice.basic.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "All details about the posts.")
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    private String description;

    // Many posts mapped to one user. Foreign key exists in this table.
    // @JsonIgnore to prevent recursive loops.
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post [description=" + description + ", id=" + id + "]";
    }

}
