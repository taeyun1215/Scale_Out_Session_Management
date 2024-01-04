package com.example.demo.post;

import com.example.demo.post.dto.PostRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void createPost(@RequestBody PostRegisterRequest request) {
        postService.createPost(request);
    }

    @GetMapping
    public Page<Post> getAllPosts(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return postService.getAllPosts(pageable);
    }
}
