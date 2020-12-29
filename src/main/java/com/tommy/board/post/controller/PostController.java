package com.tommy.board.post.controller;

import com.tommy.board.post.dto.PostSaveRequestDto;
import com.tommy.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<PostSaveRequestDto> save(@RequestBody PostSaveRequestDto postSaveRequestDto) {
        PostSaveRequestDto post = postService.save(postSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

}
