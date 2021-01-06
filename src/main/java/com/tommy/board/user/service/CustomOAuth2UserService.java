package com.tommy.board.user.service;

import com.tommy.board.user.dto.OAuthAttributeDto;
import com.tommy.board.user.dto.SessionUserDto;
import com.tommy.board.user.domain.User;
import com.tommy.board.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private static final String SESSION_KEY_USER = "user";

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        OAuthAttributeDto oAuthAttributeDto = newInstanceOfOAuthAttributeDto(oAuth2User, userRequest);
        User oAuthUser = saveOrUpdateOAuthUser(oAuthAttributeDto);

        httpSession.setAttribute(SESSION_KEY_USER, new SessionUserDto(oAuthUser));

        return new DefaultOAuth2User(Collections.singleton(
                new SimpleGrantedAuthority(oAuthUser.getRoleKey())),
                oAuthAttributeDto.getAttributes(),
                oAuthAttributeDto.getNameAttributeKey());
    }

    private OAuthAttributeDto newInstanceOfOAuthAttributeDto(OAuth2User oAuth2User, OAuth2UserRequest userRequest) {
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        return OAuthAttributeDto.of(userNameAttributeName, oAuth2User.getAttributes());
    }

    private User saveOrUpdateOAuthUser(OAuthAttributeDto authAttributeDto) {
        User user = userRepository.findByEmail(authAttributeDto.getEmail())
                .map(entity -> entity.update(authAttributeDto.getName(), authAttributeDto.getProfileImage()))
                .orElse(authAttributeDto.toEntity());

        return userRepository.save(user);
    }

}
