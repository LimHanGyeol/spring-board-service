package com.tommy.board.post.domain;

import com.tommy.board.global.BaseTimeEntity;
import com.tommy.board.post.dto.PostResponseDto;
import com.tommy.board.post.dto.PostSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 15)
    private String author;

    private Post(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public static Post write(String title, String description, String author) {
        return new Post(title, description, author);
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public PostResponseDto toPostResponseDto() {
        return new PostResponseDto(this);
    }

}
