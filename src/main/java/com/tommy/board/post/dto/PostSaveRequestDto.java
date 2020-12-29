package com.tommy.board.post.dto;

import com.tommy.board.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String title;
    private String description;
    private String author;

    private PostSaveRequestDto(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public static PostSaveRequestDto of(String title, String description, String author) {
        return new PostSaveRequestDto(title, description, author);
    }

    public Post toEntity() {
        return Post.write(title, description, author);
    }

}
