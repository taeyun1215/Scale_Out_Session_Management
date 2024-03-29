package com.example.demo.post;

import com.example.demo.post.dto.PageDTO;
import com.example.demo.post.dto.PostRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final PostCustomRepo postCustomRepo;

    @Override
    public void createPost(PostRegisterRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        postRepo.save(post);
    }

    @Override
    @Cacheable(value = "posts", key = "#pageable")
    public PageDTO<Post> getAllPosts(Pageable pageable) {
        Page<Post> posts = postCustomRepo.getAllPosts(pageable);
        return PageDTO.fromPage(posts);
    }
}
