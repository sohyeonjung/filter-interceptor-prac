package com.sh.simpleboard.board.service;

import com.sh.simpleboard.board.db.BoardEntity;
import com.sh.simpleboard.board.db.BoardRepository;
import com.sh.simpleboard.board.model.BoardDto;
import com.sh.simpleboard.board.model.BoardRequest;
import com.sh.simpleboard.crud.CRUDAbstractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService extends CRUDAbstractService<BoardDto, BoardEntity> {

}
