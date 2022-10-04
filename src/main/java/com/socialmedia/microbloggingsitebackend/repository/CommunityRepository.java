package com.socialmedia.microbloggingsitebackend.repository;

import com.socialmedia.microbloggingsitebackend.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community,Long> {
}
