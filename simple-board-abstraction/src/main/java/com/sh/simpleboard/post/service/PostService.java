package com.sh.simpleboard.post.service;

import com.sh.simpleboard.board.db.BoardRepository;
import com.sh.simpleboard.common.Api;
import com.sh.simpleboard.common.Pagination;
import com.sh.simpleboard.crud.CRUDAbstractService;
import com.sh.simpleboard.post.db.PostEntity;
import com.sh.simpleboard.post.db.PostRepository;
import com.sh.simpleboard.post.model.PostDto;
import com.sh.simpleboard.post.model.PostRequest;
import com.sh.simpleboard.post.model.PostViewRequest;
import com.sh.simpleboard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService extends CRUDAbstractService<PostDto, PostEntity> {

}
