package com.tommy.board.post.service;

import com.tommy.board.global.exception.ExceptionMessage;
import com.tommy.board.global.exception.PostNotFoundException;
import com.tommy.board.post.domain.Post;
import com.tommy.board.post.dto.PostListResponseDto;
import com.tommy.board.post.dto.PostResponseDto;
import com.tommy.board.post.dto.PostSaveRequestDto;
import com.tommy.board.post.dto.PostUpdateRequestDto;
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
        PostResponseDto postResponseDto = new PostResponseDto(Post.write(title, description, author));
        given(postService.save(saveRequestDto)).willReturn(postResponseDto);

        // when
        PostResponseDto savedPost = postService.save(saveRequestDto);
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
        Post post = Post.write(title, description, author);
        PostListResponseDto postListResponseDto = new PostListResponseDto(post);
        List<PostListResponseDto> mockPosts = Arrays.asList(postListResponseDto);

        given(postService.findAll()).willReturn(mockPosts);

        // when
        List<PostListResponseDto> posts = postService.findAll();

        assertThat(posts).hasSize(1);
    }

    @Test
    @DisplayName("특정 Id의 Post 조회")
    void findById() {
        // given
        PostResponseDto postResponseDto = newInstanceOfPostResponseDto(title, description);

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
        given(postService.findById(1234L)).willThrow(new PostNotFoundException());

        assertThatExceptionOfType(PostNotFoundException.class)
                .isThrownBy(() -> {
                    postService.findById(1234L);
                }).withMessage(ExceptionMessage.MESSAGE_NOT_FOUND_POST);
    }

    @Test
    @DisplayName("Post 수정")
    void update() {
        // given
        String updateTitle = "update title";
        String updateDescription = "update description";

        PostUpdateRequestDto updateRequestDto = new PostUpdateRequestDto(updateTitle, updateDescription);

        PostResponseDto postResponseDto = newInstanceOfPostResponseDto(updateTitle, updateDescription);

        given(postService.update(1L, updateRequestDto)).willReturn(postResponseDto);

        // when
        PostResponseDto updatedPost = postService.update(1L, updateRequestDto);

        // then
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getTitle()).isEqualTo(updateTitle);
        assertThat(updatedPost.getDescription()).isEqualTo(updateDescription);
    }

    private PostResponseDto newInstanceOfPostResponseDto(String title, String description) {
        Post post = Post.write(title, description, author);
        return new PostResponseDto(post);
    }

}
