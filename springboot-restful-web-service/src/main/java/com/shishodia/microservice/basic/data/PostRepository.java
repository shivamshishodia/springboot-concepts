package com.shishodia.microservice.basic.data;

import com.shishodia.microservice.basic.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
