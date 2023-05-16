package sk.umb.fpv.laflait.testQuestions.service;

import jakarta.transaction.Transactional;
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

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public void processAnswers(Long testID, TestQuestionRequestDTO answers) {
        List<CorrectAnswers> correctAnswers = new ArrayList<>();
        List<Object[]> tuples = questionRepository.findCorrectByTest(testID);
        for (Object[] tuple : tuples) {
            Long id = (Long) tuple[0];
            String answer = (String) tuple[1];
            CorrectAnswers correctAnswer = new CorrectAnswers();
            correctAnswer.setCorrectAnswer(answer);
            correctAnswer.setQuestionId(id);
            correctAnswers.add(correctAnswer);
        }

        List<UserAnswerEntity> result = userAnswerRepository.getAllByIdTestAndIdUser(answers.getUserId(), testID);

        if(result.size() > 0){
            userAnswerRepository.deleteAllByIdTestAndIdUser(answers.getUserId(), testID);
        }

        String[] ans = answers.getAnswer();
        Long[] ids= answers.getQuestionId();
        int numberOfCorrectAnswers = 0;

        for (int i = 0; i < ans.length; i++) {
            boolean found = false;
            for (CorrectAnswers correctAnswer : correctAnswers) {
                if (Objects.equals(correctAnswer.getCorrectAnswer(), ans[i])) {
                    // correct answer
                    UserAnswerEntity entity = mapToEntity(ans[i], "Spravne", ids[i], answers.getUserId(), testID);
                    userAnswerRepository.save(entity);
                    found = true;
                    numberOfCorrectAnswers++;
                    break;
                }
            }
            if (!found) {
                // incorrect answer
                UserAnswerEntity entity = mapToEntity(ans[i], "Nespravne", ids[i], answers.getUserId(), testID);
                userAnswerRepository.save(entity);
            }
        }
    }

    private UserAnswerEntity mapToEntity(String answer, String result, Long questionID, Long userID, Long testID){
        UserAnswerEntity entity = new UserAnswerEntity();

        entity.setAnswer(answer);
        entity.setResult(result);
        entity.setQuestionId(questionID);
        entity.setUserId(userID);
        entity.setTestId(testID);

        return entity;
    }
}
