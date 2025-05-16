package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TestDataForSending {
    private Integer testId;
    private List<TestQuestionDataForSending> testQuestionList;

    public TestDataForSending(Integer testId, List<TestQuestionDataForSending> testQuestionList) {
        this.testId = testId;
        this.testQuestionList = testQuestionList;
    }
}
