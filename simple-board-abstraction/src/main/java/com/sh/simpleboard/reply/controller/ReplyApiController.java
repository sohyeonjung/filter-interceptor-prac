package com.sh.simpleboard.reply.controller;


import com.sh.simpleboard.crud.CRUDAbstractApiController;
import com.sh.simpleboard.reply.db.ReplyEntity;
import com.sh.simpleboard.reply.model.ReplyDto;
import com.sh.simpleboard.reply.model.ReplyRequest;
import com.sh.simpleboard.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyApiController extends CRUDAbstractApiController<ReplyDto, ReplyEntity> {

//    private final ReplyService replyService;
//
//    @PostMapping("")
//    public ReplyDto create(
//            @Valid @RequestBody ReplyRequest replyRequest
//            ){
//        return replyService.create(replyRequest);
//    }

}
