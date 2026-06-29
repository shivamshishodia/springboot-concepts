package com.shishodia.microservice.basic.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.shishodia.microservice.basic.data.PostRepository;
import com.shishodia.microservice.basic.data.UserRepository;
import com.shishodia.microservice.basic.exceptions.CustomUserNotFoundException;
import com.shishodia.microservice.basic.models.Post;
import com.shishodia.microservice.basic.models.User;
import com.shishodia.microservice.basic.resources.interfaces.UserResources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResourcesImpl implements UserResources {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // HATEOAS includes link to fetchUsers() mapping as well.
    @Override
    public EntityModel<User> fetchOneUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            // HATEOAS model creation. This model is returned as response.
            EntityModel<User> model = EntityModel.of(user.get());
            // Create a link pointing to fetchUsers() method
            WebMvcLinkBuilder linkFetchAllUsers = linkTo(methodOn(this.getClass()).fetchUsers());
            // Add that reference to HATEOAS model and check the response
            model.add(linkFetchAllUsers.withRel("all-users"));
            return model;
        }
        // This will throw HttpStatus.NOT_FOUND
        throw new CustomUserNotFoundException("No user present with this id.");
    }

    // Throws CustomUserNotFoundException which then handles exception JSON response and HTTP status code
    @Override
    public List<User> fetchUsers() {
        List<User> user = userRepository.findAll();
        // If no users are found
        if (user.size() == 0) {
            // This will throw HttpStatus.NOT_FOUND
            throw new CustomUserNotFoundException("No users present.");
        }
        return user;
    }

    /*
     * Objective of createUser() is to ensure that the created user id 
     * is included in the response. REST service ideally should pass the id 
     * and URI of the new record in the response. In our case it is included 
     * under headers.location under 201 HTTP response.
     */
    @Override
    public ResponseEntity<Object> createUser(User user) {
        userRepository.save(user);
        // Servlet URI takes current URI (POST /users) and adds `/{id}` 
        // and provides the URI to fetch the added user back as response.
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(user.getId())
            .toUri();
        // Return 201 CREATED along with the id of the new record.
        return ResponseEntity.created(location).build();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
        
        // List<User> allUsers = userRepository.findAll();
        // Iterator<User> userIter = allUsers.iterator();
        // while(userIter.hasNext()) {
        //     User user = userIter.next();
        //     if (user.getId() == id) {
        //         userIter.remove();
        //         break;
        //     }
        // }
    }

    @Override
    public List<Post> fetchUserPosts(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new CustomUserNotFoundException("No user present: id-" + id);
        }
        return user.get().getPost();
    }

    @Override
    public ResponseEntity<Object> createPost(Integer id, Post post) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new CustomUserNotFoundException("No user present: id-" + id);
        }
        
        Post userPost = post;
        userPost.setUser(user.get());
        postRepository.save(userPost);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(post.getId())
            .toUri();
        // Return 201 CREATED along with the id of the new record.
        return ResponseEntity.created(location).build();
    }

}
