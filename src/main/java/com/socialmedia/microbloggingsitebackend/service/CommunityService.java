package com.socialmedia.microbloggingsitebackend.service;

import com.socialmedia.microbloggingsitebackend.dto.CommunityDto;
import com.socialmedia.microbloggingsitebackend.exceptions.SpringRedditException;
import com.socialmedia.microbloggingsitebackend.mapper.CommunityMapper;
import com.socialmedia.microbloggingsitebackend.model.Community;
import com.socialmedia.microbloggingsitebackend.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final CommunityMapper communityMapper;

    @Transactional
    public CommunityDto save(CommunityDto communityDto) {
        Community save = communityRepository.save(communityMapper.mapDtoToCommunity(communityDto));
        communityDto.setId(save.getId());
        return communityDto;
    }

    @Transactional(readOnly = true)
    public List<CommunityDto> getAll() {
        return communityRepository.findAll()
                .stream()
                .map(communityMapper::mapCommunityDto)
                .collect(toList());
    }

    public CommunityDto getCommunity(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No community found with ID - " + id));
        return communityMapper.mapCommunityDto(community);
    }
}