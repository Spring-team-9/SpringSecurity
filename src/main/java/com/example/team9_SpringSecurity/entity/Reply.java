package com.example.team9_SpringSecurity.entity;

import com.example.team9_SpringSecurity.dto.ReplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Reply extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long replyId;

    @ManyToOne
    @JoinColumn(name = "MEMO_ID", nullable = false)
    private Memo memo; //필드 하나가 아니라 객체로 연결해야함

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String replyName;

    @Column(nullable = false)
    private String replyContent;

    @OneToMany(mappedBy = "reply", cascade = CascadeType.REMOVE)
    private List<LikeReply> likereply = new ArrayList<>();

    @Formula("(select count(1) from like_Reply where like_Reply.reply_id = reply_id)")
    private int totalCommentCount;

    public Reply(ReplyRequestDto dto, User user, Memo memo){
        this.memo = memo;
        this.replyName = user.getUsername();
        this.replyContent = dto.getReplyContent();
        this.user = user;
        this.totalCommentCount = getTotalCommentCount();
    }

    public void update(ReplyRequestDto dto){
        this.replyContent = dto.getReplyContent();
    }
}
