package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TestResponseForSending {
    private Integer testId;
    private List<TestQuestionResponseForSending> testQuestionResponseList;

    public TestResponseForSending(Integer testId, List<TestQuestionResponseForSending> testQuestionResponseList) {
        this.testId = testId;
        this.testQuestionResponseList = testQuestionResponseList;
    }
}
