package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestQuestionDataForSending {
    private Integer idQuestion;

    private String text;

    private String answerOption1;
    private String answerOption2;
    private String answerOption3;
    private String answerOption4;

    public TestQuestionDataForSending(TestQuestionDTO testQuestionDTO) {
        this.idQuestion = testQuestionDTO.getIdQuestion();
        this.text = testQuestionDTO.getText();
        this.answerOption1 = testQuestionDTO.getAnswerOption1();
        this.answerOption2 = testQuestionDTO.getAnswerOption2();
        this.answerOption3 = testQuestionDTO.getAnswerOption3();
        this.answerOption4 = testQuestionDTO.getAnswerOption4();
    }

    @Override
    public String toString() {
        return "TestQuestionDataForSending{" +
                "idQuestion=" + idQuestion +
                ", text='" + text + '\'' +
                ", answerOption1='" + answerOption1 + '\'' +
                ", answerOption2='" + answerOption2 + '\'' +
                ", answerOption3='" + answerOption3 + '\'' +
                ", answerOption4='" + answerOption4 + '\'' +
                '}';
    }
}
