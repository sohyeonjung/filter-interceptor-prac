package com.sh.simpleboard.board.service;

import com.sh.simpleboard.board.db.BoardEntity;
import com.sh.simpleboard.board.db.BoardRepository;
import com.sh.simpleboard.board.model.BoardDto;
import com.sh.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    public BoardDto create(
            BoardRequest boardRequest
    ){
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();
        var saveEntity = boardRepository.save(entity);
        return boardConverter.toDto(saveEntity);
    }


    public BoardDto view(long id) {
        var entity =  boardRepository.findById(id).get();
        return boardConverter.toDto(entity);
    }
}
