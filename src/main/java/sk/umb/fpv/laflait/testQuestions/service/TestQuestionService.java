package sk.umb.fpv.laflait.testQuestions.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.testQuestions.persistance.entity.TestQuestionEntity;
import sk.umb.fpv.laflait.testQuestions.persistance.repository.TestQuestionRepository;
import sk.umb.fpv.laflait.userAnswer.persistance.entity.UserAnswerEntity;
import sk.umb.fpv.laflait.userAnswer.persistance.repository.UserAnswerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TestQuestionService {

    private final TestQuestionRepository questionRepository;
    private final UserAnswerRepository userAnswerRepository;

    public TestQuestionService(TestQuestionRepository questionRepository, UserAnswerRepository userAnswerRepository) {
        this.questionRepository = questionRepository;
        this.userAnswerRepository = userAnswerRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<TestQuestionDetailDTO> getQuestionsByTestID(Long testID) {
        return mapToDtoList(questionRepository.findAllByTest(testID));
    }

    private List<TestQuestionDetailDTO> mapToDtoList(Iterable<TestQuestionEntity> testQuestionEntities) {
        List<TestQuestionDetailDTO> tests = new ArrayList<>();

        testQuestionEntities.forEach(testQuestionEntity -> {
            TestQuestionDetailDTO dto = mapToDto(testQuestionEntity);
            tests.add(dto);
        });

        return tests;
    }

    private TestQuestionDetailDTO mapToDto(TestQuestionEntity testQuestionEntity) {
        TestQuestionDetailDTO dto = new TestQuestionDetailDTO();

        dto.setId(testQuestionEntity.getId());
        dto.setQuestion_description(testQuestionEntity.getQuestionDespricption());
        dto.setAns1(testQuestionEntity.getAns1());
        dto.setAns2(testQuestionEntity.getAns2());
        dto.setAns3(testQuestionEntity.getAns3());
        dto.setAns4(testQuestionEntity.getAns4());

        return dto;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public void processAnswers(Long testID, TestQuestionRequestDTO answers) {
        List<CorrectAnswers> correctAnswers = questionRepository.findCorrectByTest(testID);
        String[] ans = answers.getAnswer();

        for (int i = 0; i < ans.length; i++) {
            boolean found = false;
            for (CorrectAnswers correctAnswer : correctAnswers) {
                if (Objects.equals(correctAnswer.getCorrectAnswer(), ans[i])) {
                    // correct answer
                    UserAnswerEntity entity = mapToEntity(ans[i], "Spravne", answers.getQuestionId(), answers.getUserId());
                    userAnswerRepository.save(entity);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // incorrect answer
                UserAnswerEntity entity = mapToEntity(ans[i], "Nespravne", answers.getQuestionId(), answers.getUserId());
                userAnswerRepository.save(entity);
            }
        }
    }

    private UserAnswerEntity mapToEntity(String answer, String result, Long questionID, Long userID){
        UserAnswerEntity entity = new UserAnswerEntity();

        entity.setAnswer(answer);
        entity.setResult(result);
        entity.setQuestionId(questionID);
        entity.setUserId(userID);
        //TODO ZISTIT USER ID PRIAMO Z BE
        //TODO PREMAZAT USER_ODPOVEDE KED UROBI NOVY TEST

        return entity;
    }
}
