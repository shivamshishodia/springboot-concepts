package com.shishodia.microservice.basic.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/*
 * @Entity, @Id, @GeneratedValue are used for entity creation.
 * @Size, @Past are used for http request validation and throws basic.exceptions.handleMethodArgumentNotValid.
 */
@Entity
@Schema(description = "All details about the user.")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, max = 20, message = "Name should have atleast 2 characters and a maximum 20 characters.")
    private String name;
    @Past
    private Date age;

    // One user having many posts. Mapped by "user" data member in Post
    @OneToMany(mappedBy = "user")
    private List<Post> post;

    public User() {
    }

    public User(Integer id, String name, Date age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }
    
    public List<Post> getPost() {
        return post;
    }
    
    public void setPost(List<Post> post) {
        this.post = post;
    }    

    @Override
    public String toString() {
        return "Users [age=" + age + ", id=" + id + ", name=" + name + "]";
    }
    
}
