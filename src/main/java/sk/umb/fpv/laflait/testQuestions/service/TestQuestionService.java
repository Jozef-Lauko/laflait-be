package sk.umb.fpv.laflait.testQuestions.service;

import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.authentication.persistance.entity.UserEntity;
import sk.umb.fpv.laflait.authentication.persistance.repository.UserRepository;
import sk.umb.fpv.laflait.grades.persistance.entity.GradesEntity;
import sk.umb.fpv.laflait.grades.persistance.repository.GradesRepository;
import sk.umb.fpv.laflait.testQuestions.persistance.entity.TestQuestionEntity;
import sk.umb.fpv.laflait.testQuestions.persistance.repository.TestQuestionRepository;
import sk.umb.fpv.laflait.tests.persistance.entity.TestEntity;
import sk.umb.fpv.laflait.tests.persistance.repository.TestRepository;
import sk.umb.fpv.laflait.userAnswer.persistance.entity.UserAnswerEntity;
import sk.umb.fpv.laflait.userAnswer.persistance.repository.UserAnswerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TestQuestionService {

    private final TestQuestionRepository questionRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final GradesRepository gradesRepository;
    private final TestRepository testRepository;
    private final UserRepository userRepository;

    public TestQuestionService(TestQuestionRepository questionRepository, UserAnswerRepository userAnswerRepository, GradesRepository gradesRepository, TestRepository testRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.gradesRepository = gradesRepository;
        this.testRepository = testRepository;
        this.userRepository = userRepository;
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

        gradesRepository.save(mapToEntity(testID, answers.getUserId(), getGrade(numberOfCorrectAnswers)));
    }

    private GradesEntity mapToEntity(Long testID, Long userId, String grade) {
        GradesEntity entity = new GradesEntity();
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        Optional<TestEntity> testEntity = testRepository.findById(testID);

        if(userEntity.isPresent() && testEntity.isPresent()){
            entity.setUserEntity(userEntity.get());
            entity.setTestEntity(testEntity.get());
        }

        entity.setGrade(grade);

        return entity;
    }

    private String getGrade(int numberOfCorrectAnswers) {
        int totalQuestions = 10;
        double percentage = (double) numberOfCorrectAnswers / totalQuestions * 100;
        String letterGrade;

        switch ((int) percentage / 10) {
            case 10:
            case 9:
                letterGrade = "A";
                break;
            case 8:
                letterGrade = "B";
                break;
            case 7:
                letterGrade = "C";
                break;
            case 6:
                letterGrade = "D";
                break;
            default:
                letterGrade = "F";
                break;
        }

        return letterGrade;
    }

    private UserAnswerEntity mapToEntity(String answer, String result, Long questionID, Long userID, Long testID){
        UserAnswerEntity entity = new UserAnswerEntity();

        Optional<UserEntity> userEntity = userRepository.findById(userID);
        Optional<TestEntity> testEntity = testRepository.findById(testID);
        Optional<TestQuestionEntity> questionEntity = questionRepository.findById(questionID);

        entity.setAnswer(answer);
        entity.setResult(result);

        if(userEntity.isPresent() && testEntity.isPresent() && questionEntity.isPresent()){
            entity.setUserEntity(userEntity.get());
            entity.setTestEntity(testEntity.get());
            entity.setTestQuestionEntity(questionEntity.get());
        }

        return entity;
    }
}
