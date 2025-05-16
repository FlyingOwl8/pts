package org.example.controller;

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

@RestController
@RequestMapping("/testing")
@RequiredArgsConstructor
public class TestingController {
    private final AuthTokenValidationService authTokenValidationService;
    private final TestQuestionService testQuestionService;
    private final UserService userService;

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