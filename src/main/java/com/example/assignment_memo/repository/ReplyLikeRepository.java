package com.example.assignment_memo.repository;

import com.example.assignment_memo.entity.ReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {

    Optional<Long> countByReply_ReplyId(Long replyId);
    Optional<ReplyLike> findByMemo_memoIdAndReply_ReplyIdAndUser_Id(Long memoId, Long replyId, Long userId);
}
