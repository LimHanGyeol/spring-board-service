package com.tommy.board.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("User Instance 생성")
    void create() {
        User user = User.builder()
                .name("tommy")
                .email("dlagksruf19@naver.com")
                .picture("profile_image")
                .role(Role.USER)
                .build();

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("tommy");
        assertThat(user.getRoleKey()).isEqualTo("ROLE_USER");
    }

}
