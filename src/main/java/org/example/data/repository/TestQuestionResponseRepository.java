package org.example.data.repository;

import org.example.data.entity.TestQuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionResponseRepository extends JpaRepository<TestQuestionResponse, Integer> {
    List<TestQuestionResponse> findByTestId(Integer testId);
}
