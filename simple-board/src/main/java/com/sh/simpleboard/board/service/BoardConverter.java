package com.sh.simpleboard.board.service;


import com.sh.simpleboard.board.db.BoardEntity;
import com.sh.simpleboard.board.model.BoardDto;
import com.sh.simpleboard.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

//데이터를 변환해주는 역할
@Service
@RequiredArgsConstructor
public class BoardConverter {

    private final PostConverter postConverter;

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
}