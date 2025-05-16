package org.example.data.repository;

import org.example.data.entity.PostQuestionId;
import org.example.data.entity.PostQuestionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostQuestionRepository extends JpaRepository<PostQuestionMap, PostQuestionId> {
    @Query("SELECT e.questionId FROM PostQuestionMap e WHERE e.postId = ?1")
    List<Integer> findAllIds(Integer postId);
}
