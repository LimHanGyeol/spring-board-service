package com.tommy.board.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("User Instance 생성")
    void create() {
        User user = newInstance();

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("tommy");
        assertThat(user.getRoleKey()).isEqualTo("ROLE_USER");
    }

    @Test
    @DisplayName("User 정보 Update")
    void update() {
        // given
        User user = newInstance();

        String updateName = "hangyeol";
        String updateProfileImage = "profileImage";

        // when
        user.update(updateName, updateProfileImage);

        // then
        assertThat(user.getName()).isEqualTo(updateName);
        assertThat(user.getPicture()).isEqualTo(updateProfileImage);
    }

    private User newInstance() {
        return User.builder()
                .name("tommy")
                .email("dlagksruf19@naver.com")
                .picture("profile_image")
                .role(Role.USER)
                .build();
    }

}
