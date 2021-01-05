package com.tommy.board.user.domain;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        testEntityManager.clear();
    }

    @Test
    @Order(1)
    @DisplayName("User 저장")
    void save() {
        // given
        User user = newInstance();

        // when
        User savedUser = testEntityManager.persist(user);

        // then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    @Order(2)
    @DisplayName("특정 Id의 User 조회")
    void findById() {
        // given
        User savedUser = testEntityManager.persist(newInstance());

        // when
        User user = userRepository.findByEmail("dlagksruf19@naver.com")
                .orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(savedUser.getId());
        assertThat(user.getName()).isEqualTo("tommy");
    }

    @Test
    @Order(3)
    @DisplayName("전체 User 조회")
    void findAll() {
        // given
        testEntityManager.persist(newInstance());

        // when
        List<User> users = userRepository.findAll();

        // then
        assertThat(users).hasSize(1);
    }

    @Test
    @Order(4)
    @DisplayName("User 수정")
    void update() {
        // given
        User user = testEntityManager.persist(newInstance());

        String updateName = "hangyeol";
        String updateProfileImage = "update profile_image";

        // when
        user.update(updateName, updateProfileImage);

        // then
        List<User> users = userRepository.findAll();
        User savedUser = users.get(0);

        assertThat(savedUser.getName()).isEqualTo(updateName);
        assertThat(savedUser.getPicture()).isEqualTo(updateProfileImage);
    }

    @Test
    @Order(5)
    @DisplayName("User 삭제")
    void delete() {
        // given
        testEntityManager.persist(newInstance());

        List<User> savedUsers = userRepository.findAll();
        assertThat(savedUsers).hasSize(1);

        // when
        User savedUser = savedUsers.get(0);
        userRepository.delete(savedUser);

        // then
        List<User> results = userRepository.findAll();
        assertThat(results).isEmpty();
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
