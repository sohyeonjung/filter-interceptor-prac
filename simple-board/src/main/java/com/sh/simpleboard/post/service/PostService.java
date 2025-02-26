package com.sh.simpleboard.post.service;

import com.sh.simpleboard.post.db.PostEntity;
import com.sh.simpleboard.post.db.PostRepository;
import com.sh.simpleboard.post.model.PostRequest;
import com.sh.simpleboard.post.model.PostViewRequest;
import com.sh.simpleboard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ReplyService replyService;

    public PostEntity create(
            PostRequest postRequest
    ){
        var entity = PostEntity.builder()
                .boardId(1L) //<<임시 고정
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();
        return postRepository.save(entity);

    }


    public PostEntity view(PostViewRequest postViewRequest) {
        return  postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
                .map(it->{
                    //패스워드 맞는지 확인
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다 %s vd %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }

                    //답변글도 같이 적용
                    var replyList = replyService.findAllByPostId(it.getId());
                    it.setReplyList(replyList);

                    return it;
                    //게시글 존재하는지 확인
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재 하지 않습니다: "+postViewRequest.getPostId());
                        }
                );
    }


    public List<PostEntity> all() {
        //TODO 여기도 UNREGISTERED 걸러야 하는게 아닌가..?
        //return postRepository.findAll();
        return postRepository.findByStatusOrderByIdDesc("REGISTERED");
    }


    public void delete(PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map(it->{
                    //패스워드 맞는지 확인
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다 %s vd %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }
                    
                    //게시글 삭제
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    
                    return it;
                    //게시글 존재하는지 확인
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재 하지 않습니다: "+postViewRequest.getPostId());
                        }
                );
    }
}
