package com.tommy.board.post.domain;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    void tearDown() {
        testEntityManager.clear();
    }

    @Test
    @Order(1)
    @DisplayName("Post 저장")
    void save() {
        // given
        LocalDateTime now = LocalDateTime.of(2020, 12, 31, 0, 0, 0);
        Post post = newInstance();

        // when
        Post savedPost = testEntityManager.persist(post);

        // then
        assertThat(savedPost).isNotNull();
        assertThat(savedPost).isEqualTo(post);

        assertThat(savedPost.getCreatedDate()).isAfter(now);
        assertThat(savedPost.getUpdatedDate()).isAfter(now);
    }

    @Test
    @Order(2)
    @DisplayName("특정 Id의 Post 조회")
    void findById() {
        // given
        Post savedPost = testEntityManager.persist(newInstance());

        // when
        Post post = postRepository.findById(savedPost.getId())
                .orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(savedPost.getId());
        assertThat(post.getTitle()).isEqualTo("title");
    }

    @Test
    @Order(3)
    @DisplayName("전체 Post 조회")
    void findAll() {
        // given
        Post post = newInstance();
        testEntityManager.persist(post);

        // when
        List<Post> posts = postRepository.findAll();

        // then
        assertThat(posts).hasSize(1);
    }

    @Test
    @Order(4)
    @DisplayName("Post 수정")
    void update() {
        // given
        Post post = testEntityManager.persist(newInstance());

        String updateTitle = "update title";
        String updateDescription = "update description";

        // when
        post.update(updateTitle, updateDescription);

        // then
        List<Post> posts = postRepository.findAll();
        Post savedPost = posts.get(0);

        assertThat(savedPost.getTitle()).isEqualTo(updateTitle);
        assertThat(savedPost.getDescription()).isEqualTo(updateDescription);
    }

    @Test
    @Order(5)
    @DisplayName("Post 삭제")
    void delete() {
        // given
        testEntityManager.persist(newInstance());

        List<Post> savedPosts = postRepository.findAll();
        assertThat(savedPosts).hasSize(1);

        // when
        Post savedPost = savedPosts.get(0);
        postRepository.delete(savedPost);

        // then
        List<Post> results = postRepository.findAll();
        assertThat(results).isEmpty();
    }

    private Post newInstance() {
        return Post.write("title", "description", "hangyeol");
    }

}
