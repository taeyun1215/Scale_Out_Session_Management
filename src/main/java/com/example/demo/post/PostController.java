package com.example.demo.post;

import com.example.demo.post.dto.PageDTO;
import com.example.demo.post.dto.PostRegisterRequest;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final String session_key = "userId";

    @PostMapping
    public void createPost(
            HttpSession session,
            @RequestBody PostRegisterRequest request
    ) {
        Long userId = (Long) session.getAttribute(session_key);
        userService.findById(userId); // 로그인 한 유저인지 확인
        postService.createPost(request);
    }

    @GetMapping
    public PageDTO<Post> getAllPosts(
            HttpSession session,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Long userId = (Long) session.getAttribute(session_key);
        userService.findById(userId); // 로그인 한 유저인지 확인
        return postService.getAllPosts(pageable);
    }
}
