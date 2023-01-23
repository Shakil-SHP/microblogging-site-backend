package com.socialmedia.microbloggingsitebackend.service;

import com.socialmedia.microbloggingsitebackend.dto.PostRequest;
import com.socialmedia.microbloggingsitebackend.dto.PostResponse;
import com.socialmedia.microbloggingsitebackend.exceptions.CommunityNotFoundException;
import com.socialmedia.microbloggingsitebackend.exceptions.PostNotFoundException;
import com.socialmedia.microbloggingsitebackend.mapper.PostMapper;
import com.socialmedia.microbloggingsitebackend.model.Community;
import com.socialmedia.microbloggingsitebackend.model.Post;
import com.socialmedia.microbloggingsitebackend.model.User;
import com.socialmedia.microbloggingsitebackend.repository.CommunityRepository;
import com.socialmedia.microbloggingsitebackend.repository.PostRepository;
import com.socialmedia.microbloggingsitebackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        Community community = communityRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new CommunityNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, community, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Community community = communityRepository.findById(subredditId)
                .orElseThrow(() -> new CommunityNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllByCommunity(community);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}