package com.tommy.board.post.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PostTest {

    @Test
    @DisplayName("Post Instance 생성")
    void create() {
        String title = "title";
        String description = "description";
        String author = "hangyeol";

        Post post = Post.write(title, description, author);

        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getDescription()).isEqualTo(description);
        assertThat(post.getAuthor()).isEqualTo(author);
    }

}
