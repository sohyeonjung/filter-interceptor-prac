package com.sh.simpleboard.board.service;


import com.sh.simpleboard.board.db.BoardEntity;
import com.sh.simpleboard.board.model.BoardDto;
import com.sh.simpleboard.crud.Converter;
import com.sh.simpleboard.post.db.PostRepository;
import com.sh.simpleboard.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

//데이터를 변환해주는 역할
@Service
@RequiredArgsConstructor
public class BoardConverter implements Converter<BoardDto, BoardEntity> {

    private final PostConverter postConverter;
    private final PostRepository postRepository;

    @Override
    public BoardDto toDto(BoardEntity boardEntity){

        var postList = boardEntity.getPostList()
                .stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());

        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList)
                .build()
                ;
    }

    @Override
    public BoardEntity toEntity(BoardDto boardDto) {
        var postEntity = boardDto.getPostList().stream()
                .map(postConverter::toEntity)
                .collect(Collectors.toList());

        return BoardEntity.builder()
                .id(boardDto.getId())
                .boardName(boardDto.getBoardName())
                .status((boardDto.getStatus()!=null)?boardDto.getStatus():"REGISTERED")
                .postList(postEntity)
                .build();

    }
}