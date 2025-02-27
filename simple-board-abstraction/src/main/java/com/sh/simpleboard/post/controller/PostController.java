package com.sh.simpleboard.post.controller;


import com.sh.simpleboard.common.Api;
import com.sh.simpleboard.post.db.PostEntity;
import com.sh.simpleboard.post.model.PostDto;
import com.sh.simpleboard.post.model.PostRequest;
import com.sh.simpleboard.post.model.PostViewRequest;
import com.sh.simpleboard.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public PostDto create(
            @Valid @RequestBody PostRequest postRequest
    ){
        return postService.create(postRequest);
    }

    @PostMapping("/view")
    public PostDto view(
            @Valid @RequestBody PostViewRequest postViewRequest
    ){
        return postService.view(postViewRequest);
    }

    @GetMapping("/all")
    public Api<List<PostDto>> list(
            @PageableDefault(page = 0, size = 10, sort ="id", direction = Sort.Direction.DESC)
            Pageable pageable
            ){
        return postService.all(pageable);
    }

    @PostMapping("/delete")
    public void delete(
            @Valid @RequestBody PostViewRequest postViewRequest
    ){
         postService.delete(postViewRequest);
    }
}
