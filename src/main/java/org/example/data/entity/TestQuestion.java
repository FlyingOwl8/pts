package org.example.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "test_questions")
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Integer idQuestion;

    @Column(name = "text")
    private String text;

    @Column(name = "answer_option1")
    private String answerOption1;
    @Column(name = "answer_option2")
    private String answerOption2;
    @Column(name = "answer_option3")
    private String answerOption3;
    @Column(name = "answer_option4")
    private String answerOption4;

    @Column(name = "answer_accuracy1")
    private boolean answerAccuracy1;
    @Column(name = "answer_accuracy2")
    private boolean answerAccuracy2;
    @Column(name = "answer_accuracy3")
    private boolean answerAccuracy3;
    @Column(name = "answer_accuracy4")
    private boolean answerAccuracy4;
}
