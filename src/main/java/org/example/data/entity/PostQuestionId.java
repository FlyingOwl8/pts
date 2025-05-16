package org.example.data.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class PostQuestionId implements Serializable {
    private Integer postId;
    private Integer questionId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PostQuestionId that = (PostQuestionId) o;
        return Objects.equals(postId, that.postId) && Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, questionId);
    }
}
