package com.sh.simpleboard.board.controller;


import com.sh.simpleboard.board.db.BoardEntity;
import com.sh.simpleboard.board.model.BoardDto;
import com.sh.simpleboard.board.model.BoardRequest;
import com.sh.simpleboard.board.service.BoardService;
import com.sh.simpleboard.crud.CRUDAbstractApiController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController extends CRUDAbstractApiController<BoardDto, BoardEntity> {


}
