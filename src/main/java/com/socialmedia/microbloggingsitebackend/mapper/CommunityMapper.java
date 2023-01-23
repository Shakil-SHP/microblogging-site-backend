package com.socialmedia.microbloggingsitebackend.mapper;

import com.socialmedia.microbloggingsitebackend.dto.CommunityDto;
import com.socialmedia.microbloggingsitebackend.model.Community;
import com.socialmedia.microbloggingsitebackend.model.Post;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommunityMapper {
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    CommunityDto mapCommunityDto(Community community);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Community mapDtoToCommunity(CommunityDto communityDto);
}
