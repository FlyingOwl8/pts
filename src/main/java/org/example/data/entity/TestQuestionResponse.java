package org.example.data.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@IdClass(TestQuestionResponseId.class)
@Table(name = "test_user_responses")
public class TestQuestionResponse {


    @Id
    @Column(name = "test_id")
    private Integer testId;
    @Id
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "user_response1")
    private boolean userResponse1;
    @Column(name = "user_response2")
    private boolean userResponse2;
    @Column(name = "user_response3")
    private boolean userResponse3;
    @Column(name = "user_response4")
    private boolean userResponse4;
}
