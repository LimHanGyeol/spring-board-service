package com.tommy.board.post.service;

import com.tommy.board.post.dto.PostSaveRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostService postService;

    @Test
    @DisplayName("게시글 등록 기능")
    void save() {
        // given
        String title = "post title";
        String description = "post description";
        String author = "hangyeol";
        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.of(title, description, author);
        given(postService.save(postSaveRequestDto)).willReturn(postSaveRequestDto);

        // when
        PostSaveRequestDto savedPost = postService.save(postSaveRequestDto);

        // then
        assertThat(savedPost.getTitle()).isEqualTo(title);
        assertThat(savedPost.getDescription()).isEqualTo(description);
        assertThat(savedPost.getAuthor()).isEqualTo(author);
    }

}
