package com.example.demo.Repository;


import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
 //   List<User>findByUsername(String username);

    Optional<User> findByUsername(String username);



}
