package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TestQuestionResponseForSending {
    private Integer questionId;

    private boolean userResponse1;
    private boolean userResponse2;
    private boolean userResponse3;
    private boolean userResponse4;

    public TestQuestionResponseForSending(Integer questionId, boolean userResponse1, boolean userResponse2,
            boolean userResponse3, boolean userResponse4) {
        this.questionId = questionId;
        this.userResponse1 = userResponse1;
        this.userResponse2 = userResponse2;
        this.userResponse3 = userResponse3;
        this.userResponse4 = userResponse4;
    }
}
