package com.example.demo.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepo {

    Page<Post> getAllPosts(Pageable pageable);

}
