package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestCreationData {
    private Integer userId;
    private Integer diffId;

    public TestCreationData(Integer userId, Integer diffId) {
        this.userId = userId;
        this.diffId = diffId;
    }
}
