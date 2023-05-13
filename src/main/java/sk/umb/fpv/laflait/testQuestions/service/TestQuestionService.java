package sk.umb.fpv.laflait.testQuestions.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.testQuestions.persistance.entity.TestQuestionEntity;
import sk.umb.fpv.laflait.testQuestions.persistance.repository.TestQuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestQuestionService {

    private final TestQuestionRepository questionRepository;

    public TestQuestionService(TestQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
}
