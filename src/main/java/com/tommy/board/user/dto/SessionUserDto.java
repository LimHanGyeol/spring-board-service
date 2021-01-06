package com.tommy.board.user.dto;

import com.tommy.board.user.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUserDto implements Serializable {

    private final String name;
    private final String email;
    private final String profileImage;

    public SessionUserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImage = user.getProfileImage();
    }

}
