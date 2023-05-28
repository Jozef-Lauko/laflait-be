package sk.umb.fpv.laflait.tests.service;

import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.tests.persistance.entity.TestEntity;
import sk.umb.fpv.laflait.tests.persistance.repository.TestRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestDetailDTO> getAllTests() {
        return mapToDtoList(testRepository.findAll());
    }

    private List<TestDetailDTO> mapToDtoList(Iterable<TestEntity> testEntities) {
        List<TestDetailDTO> tests = new ArrayList<>();

        testEntities.forEach(testEntity -> {
            TestDetailDTO dto = mapToDto(testEntity);
            tests.add(dto);
        });

        return tests;
    }

    private TestDetailDTO mapToDto(TestEntity testEntity) {
        TestDetailDTO dto = new TestDetailDTO();

        dto.setId(testEntity.getId());
        dto.setTestDescription(testEntity.getTestDescription());

        return dto;
    }
}
