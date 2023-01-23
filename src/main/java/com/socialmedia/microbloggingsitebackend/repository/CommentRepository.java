package com.socialmedia.microbloggingsitebackend.repository;

import com.socialmedia.microbloggingsitebackend.model.Comment;
import com.socialmedia.microbloggingsitebackend.model.Post;
import com.socialmedia.microbloggingsitebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
