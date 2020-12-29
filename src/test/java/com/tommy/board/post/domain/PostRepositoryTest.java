package com.tommy.board.post.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("Post 저장")
    void save() {
        // given
        Post post = Post.write("title", "description", "hangyeol");

        // when
        Post savedPost = postRepository.save(post);

        // then
        assertThat(savedPost).isNotNull();
        assertThat(savedPost).isEqualTo(post);
    }

}
