package org.example.data.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class TestQuestionResponseId implements Serializable {
    private Integer testId;
    private Integer questionId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestionResponseId that = (TestQuestionResponseId) o;
        return Objects.equals(testId, that.testId) && Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, questionId);
    }
}
