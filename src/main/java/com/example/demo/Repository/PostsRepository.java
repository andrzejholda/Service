package com.example.demo.Repository;

import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post,Long> {
}
