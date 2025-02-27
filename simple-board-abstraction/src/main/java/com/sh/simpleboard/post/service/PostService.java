package com.sh.simpleboard.post.service;

import com.sh.simpleboard.board.db.BoardRepository;
import com.sh.simpleboard.common.Api;
import com.sh.simpleboard.common.Pagination;
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
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final PostConverter postConverter;

    public PostDto create(
            PostRequest postRequest
    ){
        var boardEntity = boardRepository.findById(postRequest.getBoardId()).get(); //<< 임시 고정
        var entity = PostEntity.builder()
                //.boardId(1L) //<<임시 고정
                .board(boardEntity)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();
        var saveentity = postRepository.save(entity);
        return postConverter.toDto(saveentity);

    }


    public PostDto view(PostViewRequest postViewRequest) {
        var entity =  postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
                .map(it->{
                    //패스워드 맞는지 확인
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다 %s vd %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }

                    //답변글도 같이 적용 -> post에 replylist가 있기 때문에 이제 이 코드 없어도 됨
//                    var replyList = replyService.findAllByPostId(it.getId());
//                    it.setReplyList(replyList);

                    return it;
                    //게시글 존재하는지 확인
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재 하지 않습니다: "+postViewRequest.getPostId());
                        }
                );
        return postConverter.toDto(entity);
    }


    public Api<List<PostDto>> all(Pageable pageable) {
//        var list = postRepository.findAll(pageable);
//
//        var pagination = Pagination.builder()
//                .page(list.getNumber())
//                .size(list.getSize())
//                .currentElements(list.getNumberOfElements())
//                .totalElements(list.getTotalElements())
//                .totalPage(list.getTotalPages())
//                .build()
//                ;
//
//        var response = Api.<List<PostEntity>>builder()
//                .body(list.toList())
//                .pagination(pagination)
//                .build();
//
//        return response;

        Page<PostEntity> entitylist = postRepository.findByStatusOrderByIdDesc("REGISTERED", pageable);

        List<PostDto> dtolist =  entitylist.stream()
                .map(it->
                {return postConverter.toDto(it);
                }).collect(Collectors.toList());

        var pagination = Pagination.builder()
                .page(entitylist.getNumber())
                .size(entitylist.getSize())
                .currentElements(entitylist.getNumberOfElements())
                .totalElements(entitylist.getTotalElements())
                .totalPage(entitylist.getTotalPages())
                .build();

        var response = Api.<List<PostDto>>builder()
                .body(dtolist)
                .pagination(pagination)
                .build();
        return response;

    }


    public void delete(PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map(it->{
                    //패스워드 맞는지 확인
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다 %s vd %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }
                    
                    //게시글 삭제
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    
                    return it;
                    //게시글 존재하는지 확인
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재 하지 않습니다: "+postViewRequest.getPostId());
                        }
                );
    }
}
