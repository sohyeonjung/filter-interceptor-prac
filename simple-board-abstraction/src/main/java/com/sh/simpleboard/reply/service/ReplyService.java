package com.sh.simpleboard.reply.service;


import com.sh.simpleboard.crud.CRUDAbstractService;
import com.sh.simpleboard.post.db.PostRepository;
import com.sh.simpleboard.reply.db.ReplyEntity;
import com.sh.simpleboard.reply.db.ReplyRepository;
import com.sh.simpleboard.reply.model.ReplyDto;
import com.sh.simpleboard.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReplyService extends CRUDAbstractService<ReplyDto, ReplyEntity>{
}
