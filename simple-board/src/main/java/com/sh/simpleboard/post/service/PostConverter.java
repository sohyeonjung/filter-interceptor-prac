package com.sh.simpleboard.post.service;

import com.sh.simpleboard.post.db.PostEntity;
import com.sh.simpleboard.post.model.PostDto;
import com.sh.simpleboard.reply.service.ReplyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostConverter {

    private final ReplyConverter replyConverter;


    public PostDto toDto(PostEntity postEntity){
        var replyList = postEntity.getReplyList().stream()
                .map(replyEntity -> replyConverter.toDto(replyEntity))
                .collect(Collectors.toList());

        return PostDto.builder()
                .id(postEntity.getId())
                .userName(postEntity.getUserName())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .password(postEntity.getPassword())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .boardId(postEntity.getBoard().getId())
                .replyList(replyList)
                .build();
    }


}
