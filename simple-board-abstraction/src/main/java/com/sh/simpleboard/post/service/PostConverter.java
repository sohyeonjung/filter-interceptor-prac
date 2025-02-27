package com.sh.simpleboard.post.service;

import com.sh.simpleboard.board.db.BoardRepository;
import com.sh.simpleboard.crud.Converter;
import com.sh.simpleboard.post.db.PostEntity;
import com.sh.simpleboard.post.model.PostDto;
import com.sh.simpleboard.reply.db.ReplyRepository;
import com.sh.simpleboard.reply.service.ReplyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostConverter implements Converter<PostDto, PostEntity> {

    private final ReplyConverter replyConverter;
    private final BoardRepository boardRepository;

    @Override
    public PostDto toDto(PostEntity postEntity) {
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

    @Override
    public PostEntity toEntity(PostDto postDto) {

        var boardEntity = boardRepository.findById(postDto.getBoardId());

        var replyEntity = postDto.getReplyList().stream()
                .map(replyDto -> replyConverter.toEntity(replyDto))
                .collect(Collectors.toList());

        return PostEntity.builder()
                .id(postDto.getId())
                .board(boardEntity.orElseGet(()->null))
                .userName(postDto.getUserName())
                .password(postDto.getPassword())
                .email(postDto.getEmail())
                .status((postDto.getStatus()!=null)?postDto.getStatus() : "REGISTERED")
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .postedAt(postDto.getPostedAt())
                .replyList(replyEntity)
                .build();
    }
}
