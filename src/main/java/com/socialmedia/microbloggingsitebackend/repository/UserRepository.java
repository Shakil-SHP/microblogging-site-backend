package com.socialmedia.microbloggingsitebackend.repository;

import com.socialmedia.microbloggingsitebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
