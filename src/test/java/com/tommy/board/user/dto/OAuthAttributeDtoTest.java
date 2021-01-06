package com.tommy.board.user.dto;

import com.tommy.board.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OAuthAttributeDtoTest {

    private Map<String, Object> attributes;

    @BeforeEach
    void setUp() {
        attributes = initAttributes();
    }

    @Test
    @DisplayName("OAuthAttributeDto 생성")
    void create() {
        OAuthAttributeDto oAuthAttributeDto = OAuthAttributeDto.of("Tommy", attributes);

        assertThat(oAuthAttributeDto).isNotNull();
        assertThat(oAuthAttributeDto.getName()).isEqualTo("Tommy");
        assertThat(oAuthAttributeDto.getEmail()).isEqualTo("hang19663@gmail.com");
    }

    @Test
    @DisplayName("OAuthAttributeDto 를 User Entity 로 변환")
    void toEntity() {
        User user = OAuthAttributeDto.of("Tommy", attributes).toEntity();

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("hang19663@gmail.com");
        assertThat(user.getName()).isEqualTo("Tommy");
    }

    private Map<String, Object> initAttributes() {
        attributes = new HashMap<>();
        attributes.put("name", "Tommy");
        attributes.put("email", "hang19663@gmail.com");
        attributes.put("picture", "profile_image");
        return attributes;
    }

}
