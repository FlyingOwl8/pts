package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.data.dto.*;
import org.example.data.entity.*;
import org.example.data.mapper.EntityDTOMapper;
import org.example.data.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestQuestionService {
    @Value("${app.tests.test-question-number}")
    private Integer testQuestionNumber;

    private final TestRepository testRepository;
    private final TestQuestionRepository testQuestionRepository;
    private final TestQuestionResponseRepository testQuestionResponseRepository;
    private final DifficultiesRepository difficultiesRepository;
    private final UserRepository userRepository;
    private final PostQuestionRepository postQuestionRepository;
    private final PostRepository postRepository;

    private final EntityDTOMapper entityDTOMapper;

    public TestQuestionDTO getTestQuestionById(Integer questionId) {
        Optional<TestQuestion> testQuestionOptional = testQuestionRepository.findById(questionId);
        if (testQuestionOptional.isEmpty()) {
            throw new RuntimeException("Вопрос не найден");
        }
        try {
            return entityDTOMapper.toTestQuestionDTO(testQuestionOptional.get());
        }
        catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки вопроса");
        }
    }

    private List<Integer> getTestQuestionsIds(Integer postId) {
//        return testQuestionRepository.findAllIds();
        return postQuestionRepository.findAllIds(postId);
    }

    private List<Integer> pickNRandomElements(List<Integer> list, int n, Random r) {
        int length = list.size();
        if (length < n) {
            return null;
        }

        for (int i = length - 1; i >= length - n; --i)
        {
            Collections.swap(list, i , r.nextInt(i + 1));
        }
        return list.subList(length - n, length);
    }

    private List<Integer> pickNRandomIds(List<Integer> list, int n) {
        return pickNRandomElements(list, n, ThreadLocalRandom.current());
    }

    private List<TestQuestionDTO> getNRandomTestQuestions(Integer postId, int n) {
        List<Integer> list = getTestQuestionsIds(postId);
        List<Integer> ids = pickNRandomIds(list, n);
        return ids.stream().
                        map(id -> entityDTOMapper.toTestQuestionDTO(testQuestionRepository.findById(id).get()))
                .toList();
    }


    public Integer createTest(
            Integer userId) {
        Test test = new Test();
        test.setUserId(userId);
        test.setStatusId(1);
        test.setResult(null);
        test.setSuccess(null);
        Integer savedTestId = testRepository.save(test).getIdTest();
        return savedTestId;
    }


    public TestDataForSending generateTestQuestions(Integer savedTestId) {
        Integer userId = testRepository.findById(savedTestId).get().getUserId();
        Integer postId = userRepository.findById(userId).get().getPostId();

        List<TestQuestionDataForSending> testQuestionDataForSendingList =
                getNRandomTestQuestions(postId, testQuestionNumber)
                .stream().map(TestQuestionDataForSending::new).toList();

        return new TestDataForSending(savedTestId, testQuestionDataForSendingList);
    }

    public void saveTestQuestionResponse(TestQuestionResponseDTO testQuestionResponseDTO) {

        try {
            TestQuestionResponse testQuestionResponse = entityDTOMapper.toTestUserResponse(testQuestionResponseDTO);

            try {
                System.out.println(testQuestionResponse);
                testQuestionResponseRepository.save(testQuestionResponse);
            }
            catch (Exception e) {
                throw new RuntimeException("Ошибка сохранения ответа");
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки ответа");
        }
    }

    public void checkIfTestMatchesUserAndStatus(Integer testId) {
        Test test = testRepository.findById(testId).get();
        if (test.getStatusId() != 1) {
            throw new RuntimeException("Тест в неподходящем состоянии");
        }
    }

    public void saveTestQuestionResponses(TestResponseForSending testResponse) {
        Integer testId = testResponse.getTestId();


        checkIfTestMatchesUserAndStatus(testId);

        List<TestQuestionResponseDTO> testQuestionResponseDTOS = testResponse.getTestQuestionResponseList().stream()
                .map(x -> new TestQuestionResponseDTO(testId, x)).toList();

        testQuestionResponseDTOS.forEach(this::saveTestQuestionResponse);

        Test test = testRepository.findById(testId).get();
        test.setStatusId(2);
        Date now = new Date();
        test.setPassDate(new java.sql.Date(now.getTime()));
        testRepository.save(test);
    }

    public float checkTestQuestionResponse(TestQuestionResponse testQuestionResponse) {
        TestQuestion testQuestion = testQuestionRepository.findById(testQuestionResponse.getQuestionId()).get();
        if (testQuestion.isAnswerAccuracy1() == testQuestionResponse.isUserResponse1() &&
                testQuestion.isAnswerAccuracy2() == testQuestionResponse.isUserResponse2() &&
                testQuestion.isAnswerAccuracy3() == testQuestionResponse.isUserResponse3() &&
                testQuestion.isAnswerAccuracy4() == testQuestionResponse.isUserResponse4()) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public float calculateUserTestResult(Integer testId) {
        List<TestQuestionResponse> testQuestionResponses = testQuestionResponseRepository.findByTestId(testId);
        float result = 0;
        for (TestQuestionResponse response: testQuestionResponses) {
            result += checkTestQuestionResponse(response);
        }
        return result;
    }

    public void saveUserTestResult(Integer testId) {
        Test test = testRepository.findById(testId).get();
        if (test.getStatusId() != 2) {
            throw new RuntimeException("Тест в неподходящем состоянии");
        }

        float testResult = calculateUserTestResult(testId);
        test.setResult(testResult);

        Integer userId = test.getUserId();
        Integer postId = userRepository.findById(userId).get().getPostId();
        Integer difficultyId = postRepository.findById(postId).get().getDiffId();
        Difficulty difficulty = difficultiesRepository.findById(difficultyId).get();

        if (testResult/testQuestionNumber < difficulty.getThreshold()) {
            test.setSuccess(false);
        }
        else {
            test.setSuccess(true);
        }

        test.setStatusId(3);
        testRepository.save(test);
    }

    public TestDTO getTestDataById(Integer testId) {
        Test test = testRepository.findById(testId).get();
        System.out.println(test);
        TestDTO testDTO = entityDTOMapper.toTestDTO(test);
        System.out.println(testDTO);
        return testDTO;
    }

    public List<TestDTO> getTestsDataForUser(Integer userId) {
        List<Test> testList = testRepository.findTestsByUserId(userId);
        List<TestDTO> testDTOList = entityDTOMapper.toListTestDTO(testList);
        return testDTOList;
    }

    public List<TestQuestionResponseDTO> getTestResponses(Integer testId) {
        return testQuestionResponseRepository.findByTestId(testId).stream()
                .map(entityDTOMapper::toTestQuestionResponseDTO)
                .collect(Collectors.toList());
    }
}
