package com.sh.simpleboard.board.controller;


import com.sh.simpleboard.board.db.BoardEntity;
import com.sh.simpleboard.board.model.BoardDto;
import com.sh.simpleboard.board.model.BoardRequest;
import com.sh.simpleboard.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardDto create(
            @Valid @RequestBody BoardRequest boardRequest
    ){
        return boardService.create(boardRequest);
    }

    @GetMapping("/id/{id}")
    public BoardDto view(
            @PathVariable long id
    ){
        return boardService.view(id);
    }


}
