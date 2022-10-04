package com.socialmedia.microbloggingsitebackend.repository;

import com.socialmedia.microbloggingsitebackend.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long> {
}
