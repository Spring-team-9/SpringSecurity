package com.example.assignment_memo.dto;

import com.example.assignment_memo.entity.Reply;
import lombok.Getter;

@Getter
public class ReplyResponseDto {

    private Long replyId;
    private Long memoId;
    private String replyName;
    private String replyContent;
    private Long likeCnt;

    public ReplyResponseDto(Reply reply) {
        this.replyId = reply.getReplyId();
        this.memoId = reply.getMemo().getMemoId();
        this.replyName = reply.getReplyName();
        this.replyContent = reply.getReplyContent();
    }

    public ReplyResponseDto(Reply reply, Long likeCnt) {
        this.replyId = reply.getReplyId();
        this.memoId = reply.getMemo().getMemoId();
        this.replyName = reply.getReplyName();
        this.replyContent = reply.getReplyContent();
        this.likeCnt = likeCnt;
    }
}
