package com.socialmedia.microbloggingsitebackend.model;

import java.util.Arrays;

public enum VoteType {
    UPVOTE(1),
    DOWNVOTE(-1);

    private  int direction;

    VoteType(int direction) {
        this.direction = direction;
    }

    public static VoteType lookup(Integer direction){
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDerection().equals(direction))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Vote not found."));
    }

    private Integer getDerection() {
        return direction;
    }

}
