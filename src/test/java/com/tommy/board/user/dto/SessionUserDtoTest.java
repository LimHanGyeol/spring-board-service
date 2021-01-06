package com.tommy.board.user.dto;

import com.tommy.board.user.domain.Role;
import com.tommy.board.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionUserDtoTest {

    @Test
    @DisplayName("Session User 객체 생성")
    void create() {
        // given
        User user = User.builder()
                .name("Tommy")
                .email("hang19663@gmail.com")
                .profileImage("profile_image")
                .role(Role.ADMIN)
                .build();

        // then
        SessionUserDto sessionUserDto = new SessionUserDto(user);

        // then
        assertThat(sessionUserDto).isNotNull();
        assertThat(sessionUserDto.getName()).isEqualTo("Tommy");
        assertThat(sessionUserDto.getEmail()).isEqualTo("hang19663@gmail.com");
    }

}
