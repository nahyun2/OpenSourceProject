package com.example.exerciseboard.repository;

import com.example.exerciseboard.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByLocation(String location);
}
