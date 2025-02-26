package com.sh.simpleboard.reply.controller;


import com.sh.simpleboard.reply.db.ReplyEntity;
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
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping("")
    public ReplyEntity create(
            @Valid @RequestBody ReplyRequest replyRequest
            ){
        return replyService.create(replyRequest);
    }

}
