package com.sh.simpleboard.post.controller;


import com.sh.simpleboard.common.Api;
import com.sh.simpleboard.crud.CRUDAbstractApiController;
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
public class PostController extends CRUDAbstractApiController<PostDto, PostEntity> {

}
