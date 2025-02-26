package com.sh.simpleboard.post.controller;


import com.sh.simpleboard.post.db.PostEntity;
import com.sh.simpleboard.post.model.PostRequest;
import com.sh.simpleboard.post.model.PostViewRequest;
import com.sh.simpleboard.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public PostEntity create(
            @Valid @RequestBody PostRequest postRequest
    ){
        return postService.create(postRequest);
    }

    @PostMapping("/view")
    public PostEntity view(
            @Valid @RequestBody PostViewRequest postViewRequest
    ){
        return postService.view(postViewRequest);
    }

    @GetMapping("/all")
    public List<PostEntity> list(){
        return postService.all();
    }

    @PostMapping("/delete")
    public void delete(
            @Valid @RequestBody PostViewRequest postViewRequest
    ){
         postService.delete(postViewRequest);
    }
}
