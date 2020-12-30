package com.tommy.board.post.dto;

import com.tommy.board.post.domain.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PostResponseDto {

    private Long id;
    private String title;
    private String description;
    private String author;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.author = post.getAuthor();
    }

}
