package com.sh.simpleboard.reply.service;

import com.sh.simpleboard.reply.db.ReplyEntity;
import com.sh.simpleboard.reply.model.ReplyDto;
import org.springframework.stereotype.Service;

@Service
public class ReplyConverter {

    public ReplyDto toDto(ReplyEntity replyEntity){

        return ReplyDto.builder()
                .id(replyEntity.getId())
                .postId(replyEntity.getId())
                .userName(replyEntity.getUserName())
                .password(replyEntity.getPassword())
                .status(replyEntity.getStatus())
                .title(replyEntity.getTitle())
                .content(replyEntity.getContent())
                .repliedAt(replyEntity.getRepliedAt())
                .build();


    }
}
