package org.example.data.repository;

import org.example.data.entity.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion, Integer> {
    @Query("SELECT e.id FROM TestQuestion e")
    List<Integer> findAllIds();
}
