package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestIdDTO {
    private Integer testId;

    public TestIdDTO(Integer testId) {
        this.testId = testId;
    }
}
