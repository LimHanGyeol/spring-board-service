package com.tommy.board.post.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("Post 저장")
    void save() {
        // given
        String postTitle = "post title";
        String postDescription = "post description";
        String postAuthor = "hangyeol";

        postRepository.save(Post.write(postTitle, postDescription, postAuthor));

        // when
        List<Post> posts = postRepository.findAll();

        // then
        assertThat(posts).hasSize(1);

        Post post = posts.get(0);
        assertThat(post.getTitle()).isEqualTo(postTitle);
        assertThat(post.getDescription()).isEqualTo(postDescription);
        assertThat(post.getAuthor()).isEqualTo(postAuthor);
    }

}
