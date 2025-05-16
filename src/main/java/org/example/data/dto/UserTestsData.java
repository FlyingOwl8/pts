package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserTestsData {
    private List<TestDTO> testList;

    public UserTestsData(List<TestDTO> testList) {
        this.testList = testList;
    }
}
