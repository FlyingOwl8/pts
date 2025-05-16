package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestQuestionDTO {
    private Integer idQuestion;

    private String text;

    private String answerOption1;
    private String answerOption2;
    private String answerOption3;
    private String answerOption4;

    private boolean answerAccuracy1;
    private boolean answerAccuracy2;
    private boolean answerAccuracy3;
    private boolean answerAccuracy4;
}
