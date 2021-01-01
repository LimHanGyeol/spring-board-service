package com.tommy.board.post.dto;

import com.tommy.board.post.domain.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PostListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime createdDate;

    public PostListResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.createdDate = post.getCreatedDate();
    }

}
