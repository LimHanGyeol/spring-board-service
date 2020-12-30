package com.tommy.board.post.controller;

import com.tommy.board.post.dto.PostResponseDto;
import com.tommy.board.post.dto.PostSaveRequestDto;
import com.tommy.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<PostResponseDto>> findAll() {
        return ResponseEntity.ok().body(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> showPost(@PathVariable Long id) {
        PostResponseDto response = postService.findById(id);
        return ResponseEntity.ok().body(response);
    }

}
