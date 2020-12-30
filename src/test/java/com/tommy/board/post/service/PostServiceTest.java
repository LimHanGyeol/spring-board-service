package com.tommy.board.post.service;

import com.tommy.board.post.domain.Post;
import com.tommy.board.post.dto.PostResponseDto;
import com.tommy.board.post.dto.PostSaveRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostService postService;

    private String title;
    private String description;
    private String author;

    @BeforeEach
    void setUp() {
        title = "post title";
        description = "post description";
        author = "hangyeol";
    }

    @Test
    @DisplayName("게시글 등록 기능")
    void save() {
        // given
        PostSaveRequestDto saveRequestDto = PostSaveRequestDto.of(title, description, author);
        given(postService.save(saveRequestDto)).willReturn(saveRequestDto);

        // when
        PostSaveRequestDto savedPost = postService.save(saveRequestDto);
        System.out.println(savedPost);
        // then
        assertThat(savedPost.getTitle()).isEqualTo(title);
        assertThat(savedPost.getDescription()).isEqualTo(description);
        assertThat(savedPost.getAuthor()).isEqualTo(author);
    }

    @Test
    @DisplayName("전체 Post 조회")
    void findAll() {
        // given
        PostResponseDto postResponseDto = newInstanceOfPostResponseDto();
        List<PostResponseDto> mockPosts = Arrays.asList(postResponseDto);

        given(postService.findAll()).willReturn(mockPosts);

        // when
        List<PostResponseDto> posts = postService.findAll();

        assertThat(posts).hasSize(1);
    }

    @Test
    @DisplayName("특정 Id의 Post 조회")
    void findById() {
        // given
        PostResponseDto postResponseDto = newInstanceOfPostResponseDto();

        given(postService.findById(1L)).willReturn(postResponseDto);

        // when
        PostResponseDto savedPost = postService.findById(1L);

        // then
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getTitle()).isEqualTo(title);
        assertThat(savedPost.getDescription()).isEqualTo(description);
    }

    @Test
    @DisplayName("존재하지 않는 Post 조회 시 Exception 발생")
    void notFoundPost() {
        // given
        given(postService.findById(1234L)).willThrow(new IllegalArgumentException("해당 게시물을 찾을 수 없습니다."));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    postService.findById(1234L);
                });
    }

    private PostResponseDto newInstanceOfPostResponseDto() {
        Post post = Post.write(title, description, author);
        return new PostResponseDto(post);
    }

}
