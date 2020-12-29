package com.tommy.board.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.board.post.domain.Post;
import com.tommy.board.post.domain.PostRepository;
import com.tommy.board.post.dto.PostSaveRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("게시물 등록 Api 호출")
    void postSave() throws Exception {
        // given
        String title = "post title";
        String description = "post description";
        String author = "hangyeol";
        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.of(title, description, author);

        String url = "http://localhost:" + port + "/api/posts";

        // when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(postSaveRequestDto)))
                .andExpect(status().isCreated());

        // then
        List<Post> posts = postRepository.findAll();
        Post post = posts.get(0);

        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getDescription()).isEqualTo(description);
    }

}
