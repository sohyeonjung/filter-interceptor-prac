package com.sh.simpleboard.reply.service;


import com.sh.simpleboard.crud.CRUDAbstractService;
import com.sh.simpleboard.post.db.PostRepository;
import com.sh.simpleboard.reply.db.ReplyEntity;
import com.sh.simpleboard.reply.db.ReplyRepository;
import com.sh.simpleboard.reply.model.ReplyDto;
import com.sh.simpleboard.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReplyService extends CRUDAbstractService<ReplyDto, ReplyEntity>{
//    private final ReplyRepository replyRepository;
//    private final PostRepository postRepository;
//    private final ReplyConverter replyConverter;
//
//    public ReplyDto create(
//            ReplyRequest replyRequest
//    ){
//        var postEntity = postRepository.findById(replyRequest.getPostId());
//
//        if(postEntity.isEmpty()){
//            throw new RuntimeException("게시물이 존재 하지 않습니다: "+replyRequest.getPostId());
//        }
//
//        var entity = ReplyEntity.builder()
//                .post(postEntity.get())
//                .userName(replyRequest.getUserName())
//                .password(replyRequest.getPassword())
//                .status("REGISTERED")
//                .title(replyRequest.getTitle())
//                .content(replyRequest.getContent())
//                .repliedAt(LocalDateTime.now())
//                .build();
//
//        var saveentity = replyRepository.save(entity);
//        return replyConverter.toDto(saveentity);
//    }
//
//    public List<ReplyDto> findAllByPostId(Long postId) {
//        var list = replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED");
//        return list.stream()
//                .map(it->{
//                    return replyConverter.toDto(it);
//                }).collect(Collectors.toList());
//    }

}
