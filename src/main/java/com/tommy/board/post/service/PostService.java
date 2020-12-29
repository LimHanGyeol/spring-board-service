package com.tommy.board.post.service;

import com.tommy.board.post.domain.PostRepository;
import com.tommy.board.post.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostSaveRequestDto save(PostSaveRequestDto postSaveRequestDto) {
        return postRepository.save(postSaveRequestDto.toEntity())
                .toPostDto();
    }

}
