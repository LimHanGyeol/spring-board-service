package com.tommy.board.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleTest {

    @Test
    @DisplayName("Role 생성 및 확인")
    void create() {
        Role role = Role.USER;
        assertThat(role.getKey()).isEqualTo("ROLE_USER");
        assertThat(role.getTitle()).isEqualTo("사용자");
    }

}
