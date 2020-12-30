package com.tommy.board.post.service;

import com.tommy.board.global.exception.ExceptionMessage;
import com.tommy.board.post.domain.Post;
import com.tommy.board.post.domain.PostRepository;
import com.tommy.board.post.dto.PostResponseDto;
import com.tommy.board.post.dto.PostSaveRequestDto;
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
    public PostSaveRequestDto save(PostSaveRequestDto postSaveRequestDto) {
        return postRepository.save(postSaveRequestDto.toEntity())
                .toPostDto();
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MESSAGE_NOT_FOUND_POST));

        return new PostResponseDto(post);
    }

}
