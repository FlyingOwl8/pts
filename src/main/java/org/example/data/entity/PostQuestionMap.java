package org.example.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@IdClass(PostQuestionId.class)
@Table(name = "post_question_map")
public class PostQuestionMap {
    @Id
    private Integer postId;
    @Id
    private Integer questionId;
}