package org.example.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.example.data.dto.*;
import org.example.data.entity.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntityDTOMapper {
    TestQuestionDTO toTestQuestionDTO(TestQuestion testQuestion);

    TestQuestionResponse toTestUserResponse(TestQuestionResponseDTO testUserResponse);
    TestQuestionResponseDTO toTestQuestionResponseDTO(TestQuestionResponse testQuestionResponse);

    TestDTO toTestDTO(Test test);
    List<TestDTO> toListTestDTO(List<Test> testList);
}
