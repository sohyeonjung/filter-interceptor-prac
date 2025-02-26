package com.sh.simpleboard.board.service;

import com.sh.simpleboard.board.db.BoardEntity;
import com.sh.simpleboard.board.db.BoardRepository;
import com.sh.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardEntity create(
            BoardRequest boardRequest
    ){
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();
        return boardRepository.save(entity);
    }



}
