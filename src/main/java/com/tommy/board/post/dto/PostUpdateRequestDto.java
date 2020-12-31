package com.tommy.board.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {

    private String title;
    private String description;

    public PostUpdateRequestDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
