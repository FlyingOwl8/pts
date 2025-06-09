package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.data.dto.*;
import org.example.data.dto.security.AuthorizationToken;
import org.example.service.TestQuestionService;
import org.example.service.UserService;
import org.example.service.security.AuthTokenValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TestingController", description = "выполняет операции процесса тестирования")
@RestController
@RequestMapping("/testing")
@RequiredArgsConstructor
public class TestingController {
    private final AuthTokenValidationService authTokenValidationService;
    private final TestQuestionService testQuestionService;
    private final UserService userService;

    @Operation(
            summary = "Создание теста для указанного пользователя",
            description = "Позволяет создать тест для указанного пользователя"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/createTestForUser")
    public ResponseEntity<?> createTestForUser(@RequestHeader("Authorization") String token,
                                                    @RequestBody UserIdDTO userIdDTO) {
        try {
            AuthorizationToken authorizationToken = authTokenValidationService.decodeToken(token);
            if (userService.checkAdminRole(authorizationToken.getSubject())) {
                Integer testId = testQuestionService.createTest(userIdDTO.getUserId());
                return ResponseEntity.ok(testId);
            } else {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("Нет прав");
            }
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }


    @Operation(
            summary = "Создание теста для текущего пользователя",
            description = "Позволяет создать тест для текущего пользователя"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/createTest")
    public ResponseEntity<?> createTest(@RequestHeader("Authorization") String token) {
        try {
            AuthorizationToken authorizationToken = authTokenValidationService.decodeToken(token);
            System.out.println(authorizationToken);

            Integer testId = testQuestionService.createTest(authorizationToken.getSubject());
            return ResponseEntity.ok(testId);
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }


    @Operation(
            summary = "Генерация набора вопросов для теста",
            description = "Позволяет сгенерировать набор вопросов для теста"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/generateTestQuestions")
    public ResponseEntity<?> generateTestQuestions(@RequestHeader("Authorization") String token,
                                                    @RequestBody TestIdDTO testIdDTO) {
        try {
            AuthorizationToken authorizationToken = authTokenValidationService.decodeToken(token);

            return ResponseEntity.ok(testQuestionService.generateTestQuestions(testIdDTO.getTestId()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }


    @Operation(
            summary = "Сохранение ответов пользователя на вопросы теста",
            description = "Позволяет сохранить ответы пользователя на вопросы теста"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/saveResponses")
    public ResponseEntity<?> saveResponses(@RequestHeader("Authorization") String token,
                              @RequestBody TestResponseForSending testResponseForSending) {
        try {
            AuthorizationToken authorizationToken = authTokenValidationService.decodeToken(token);

            testQuestionService.saveTestQuestionResponses(testResponseForSending);
            testQuestionService.saveUserTestResult(testResponseForSending.getTestId());
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }


    @Operation(
            summary = "Получение данных о заданном тесте",
            description = "Позволяет получить данные о заданном тесте"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/getTestData")
    public ResponseEntity<?> getTestData(@RequestHeader("Authorization") String token,
                               @RequestBody TestIdDTO testIdDTO) {
        try {
            AuthorizationToken authorizationToken = authTokenValidationService.decodeToken(token);

            return ResponseEntity.ok(testQuestionService.getTestDataById(testIdDTO.getTestId()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }


    @Operation(
            summary = "Получение данных о тестах заданного пользователя",
            description = "Позволяет получить данные о тестах заданного пользователя"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/getUserTestsData")
    public ResponseEntity<?> getUserTestsData(@RequestHeader("Authorization") String token,
                                              @RequestBody Integer id) {
        try {
            AuthorizationToken authorizationToken = authTokenValidationService.decodeToken(token);
            if (userService.checkAdminRole(authorizationToken.getSubject())) {
                return ResponseEntity.ok(
                        new UserTestsData(testQuestionService.getTestsDataForUser(id))
                );
            } else {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("Нет прав");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }


    @Operation(
            summary = "Получение данных об ответах пользователя на тест",
            description = "Позволяет получить данные об ответах пользователя на тест"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/getTestResponses")
    public ResponseEntity<?> getTestResponses(@RequestHeader("Authorization") String token,
                                                          @RequestBody TestIdDTO testIdDTO) {
        try {
            AuthorizationToken authorizationToken = authTokenValidationService.decodeToken(token);
            if (userService.checkAdminRole(authorizationToken.getSubject())) {
                return ResponseEntity.ok(testQuestionService.getTestResponses(testIdDTO.getTestId()));
            }
            else {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("Нет прав");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }


    @Operation(
            summary = "Получение данных о тестовом вопросе",
            description = "Позволяет получить данных о тестовом вопросе"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/getTestQuestion")
    public ResponseEntity<?> getTestResponses(@RequestHeader("Authorization") String token,
                                              @RequestBody Integer id) {
        try {
            AuthorizationToken authorizationToken = authTokenValidationService.decodeToken(token);
            if (userService.checkAdminRole(authorizationToken.getSubject())) {
                TestQuestionDTO questionDTO = testQuestionService.getTestQuestionById(id);
                return ResponseEntity.ok(questionDTO);
            } else {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("Нет прав");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }
}