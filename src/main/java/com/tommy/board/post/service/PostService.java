package com.tommy.board.post.service;

import com.tommy.board.global.exception.PostNotFoundException;
import com.tommy.board.post.domain.Post;
import com.tommy.board.post.domain.PostRepository;
import com.tommy.board.post.dto.PostListResponseDto;
import com.tommy.board.post.dto.PostResponseDto;
import com.tommy.board.post.dto.PostSaveRequestDto;
import com.tommy.board.post.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto save(PostSaveRequestDto postSaveRequestDto) {
        return postRepository.save(postSaveRequestDto.toEntity())
                .toPostResponseDto();
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAll() {
        return postRepository.findAll().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        return postRepository.findById(id)
                .map(PostResponseDto::new)
                .orElseThrow(PostNotFoundException::new);
    }

    @Transactional
    public PostResponseDto update(Long id, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        post.update(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getDescription());
        return new PostResponseDto(post);
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        postRepository.delete(post);
    }

}
