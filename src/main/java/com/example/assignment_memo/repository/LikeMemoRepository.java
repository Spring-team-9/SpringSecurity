package com.example.assignment_memo.repository;

import com.example.assignment_memo.entity.LikeMemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeMemoRepository extends JpaRepository<LikeMemo, Long> {

    Optional<Long> countByMemo_MemoId(Long memoId);
    Optional<LikeMemo> findByMemo_MemoIdAndUser_Id(Long memoId, Long userId);
}
