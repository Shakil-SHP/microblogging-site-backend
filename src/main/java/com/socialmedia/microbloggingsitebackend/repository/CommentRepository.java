package com.socialmedia.microbloggingsitebackend.repository;

import com.socialmedia.microbloggingsitebackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
