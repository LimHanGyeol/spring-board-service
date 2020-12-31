package com.tommy.board.post.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PostTest {


    private String title;
    private String description;
    private String author;

    @BeforeEach
    void setUp() {
        title = "title";
        description = "description";
        author = "hangyeol";
    }

    @Test
    @DisplayName("Post Instance 생성")
    void create() {
        Post post = Post.write(title, description, author);

        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getDescription()).isEqualTo(description);
        assertThat(post.getAuthor()).isEqualTo(author);
    }

    @Test
    @DisplayName("Post 내용 Update")
    void update() {
        // given
        Post post = Post.write(title, description, author);

        String updateTitle = "update title";
        String updateDescription = "update description";

        // when
        post.update(updateTitle, updateDescription);

        // then
        assertThat(post.getTitle()).isEqualTo(updateTitle);
        assertThat(post.getDescription()).isEqualTo(updateDescription);
    }

}
