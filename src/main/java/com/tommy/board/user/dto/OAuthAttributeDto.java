package com.tommy.board.user.dto;

import com.tommy.board.user.domain.Role;
import com.tommy.board.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributeDto {

    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String profileImage;
    private final Map<String, Object> attributes;

    @Builder
    public OAuthAttributeDto(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileImage) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    public static OAuthAttributeDto of(String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributeDto ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributeDto.builder()
                .nameAttributeKey(userNameAttributeName)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImage((String) attributes.get("picture"))
                .attributes(attributes)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .profileImage(profileImage)
                .role(Role.ADMIN)
                .build();
    }

}
