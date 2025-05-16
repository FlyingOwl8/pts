package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class TestQuestionResponseDTO {

    private Integer testId;
    private Integer questionId;

    private boolean userResponse1;
    private boolean userResponse2;
    private boolean userResponse3;
    private boolean userResponse4;

    public TestQuestionResponseDTO(Integer testId, TestQuestionResponseForSending testQuestionResponse) {
        this.testId = testId;
        this.questionId = testQuestionResponse.getQuestionId();
        this.userResponse1 = testQuestionResponse.isUserResponse1();
        this.userResponse2 = testQuestionResponse.isUserResponse2();
        this.userResponse3 = testQuestionResponse.isUserResponse3();
        this.userResponse4 = testQuestionResponse.isUserResponse4();
    }
}
