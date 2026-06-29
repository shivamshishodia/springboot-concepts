package com.shishodia.microservice.basic.resources.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.shishodia.microservice.basic.models.Post;
import com.shishodia.microservice.basic.models.User;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "api/")
public interface UserResources {
    
    @GetMapping(path = "users/{id}")
    public EntityModel<User> fetchOneUser(@PathVariable Integer id);

    @GetMapping(path = "users")
    public List<User> fetchUsers();

    // @Valid validator in place. 
    @PostMapping(path = "users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user);

    @DeleteMapping(path = "users/{id}")
    public void deleteUser(@PathVariable Integer id);

    @GetMapping(path = "users/{id}/posts")
    public List<Post> fetchUserPosts(@PathVariable Integer id);

    @PostMapping(path = "users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id, @RequestBody Post post);

}
