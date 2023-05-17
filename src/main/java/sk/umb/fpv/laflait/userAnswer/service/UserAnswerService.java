package sk.umb.fpv.laflait.userAnswer.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.userAnswer.persistance.entity.UserAnswerEntity;
import sk.umb.fpv.laflait.userAnswer.persistance.repository.UserAnswerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;

    public UserAnswerService(UserAnswerRepository userAnswerRepository) {
        this.userAnswerRepository = userAnswerRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<UserAnswerDTO> getAllAnswers() {
        return mapToDtoList(userAnswerRepository.findAll());
    }

    private List<UserAnswerDTO> mapToDtoList(Iterable<UserAnswerEntity> entities) {
        List<UserAnswerDTO> answers = new ArrayList<>();

        entities.forEach(userAnswerEntity -> {
            UserAnswerDTO dto = mapToDto(userAnswerEntity);
            answers.add(dto);
        });

        return answers;
    }

    private UserAnswerDTO mapToDto(UserAnswerEntity userAnswerEntity) {
        UserAnswerDTO dto = new UserAnswerDTO();

        dto.setId(userAnswerEntity.getId());
        dto.setUserId(userAnswerEntity.getUserId());
        dto.setQuestionId(userAnswerEntity.getQuestionId());
        dto.setTestId(userAnswerEntity.getTestId());
        dto.setResult(userAnswerEntity.getResult());

        return dto;
    }
}
