package com.sh.simpleboard.post.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sh.simpleboard.board.db.BoardEntity;
import com.sh.simpleboard.reply.db.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Long boardId;
    @ManyToOne()
    @JsonIgnore //무한반복 방지
    @ToString.Exclude //log 무한반복 방지
    private BoardEntity board; //board+_id = board_d (system 상에서)

    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime postedAt;

    //@Transient //column으로 인식지하지 않음
    @OneToMany(mappedBy = "post")
    @Builder.Default
    private List<ReplyEntity> replyList = new ArrayList<>();
}
