package com.example.demo.post;

import com.example.demo.post.dto.PostRegisterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    void createPost(PostRegisterRequest request);
    Page<Post> getAllPosts(Pageable pageable);

}
