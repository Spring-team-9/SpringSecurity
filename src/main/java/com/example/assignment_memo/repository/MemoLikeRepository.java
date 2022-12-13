package com.example.assignment_memo.repository;

import com.example.assignment_memo.entity.MemoLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoLikeRepository extends JpaRepository<MemoLike, Long> {

    Optional<Long> countByMemo_MemoId(Long memoId);
    Optional<MemoLike> findByMemo_MemoIdAndUser_Id(Long memoId, Long userId);
}
