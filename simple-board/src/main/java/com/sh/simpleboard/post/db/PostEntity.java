package com.sh.simpleboard.post.db;

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

    private Long boardId;
    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime postedAt;

    @Transient //column으로 인식지하지 않음
    private List<ReplyEntity> replyList = new ArrayList<>();
}
