package com.example.assignment_memo.repository;

import com.example.assignment_memo.entity.LikeReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeReplyRepository extends JpaRepository<LikeReply, Long> {

    Optional<Long> countByReply_ReplyId(Long replyId);
    Optional<LikeReply> findByMemo_memoIdAndReply_ReplyIdAndUser_Id(Long memoId, Long replyId, Long userId);
}
