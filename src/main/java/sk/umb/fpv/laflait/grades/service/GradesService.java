package sk.umb.fpv.laflait.grades.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.grades.persistance.entity.GradesEntity;
import sk.umb.fpv.laflait.grades.persistance.repository.GradesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradesService {

    private final GradesRepository gradesRepository;

    public GradesService(GradesRepository gradesRepository) {
        this.gradesRepository = gradesRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<GradesDetailDTO> getAllGrades() {
        return mapToDtoList(gradesRepository.findAll());
    }

    private List<GradesDetailDTO> mapToDtoList(Iterable<GradesEntity> gradesEntities) {
        List<GradesDetailDTO> grades = new ArrayList<>();

        gradesEntities.forEach(gradesEntity -> {
            GradesDetailDTO dto = mapToDto(gradesEntity);
            grades.add(dto);
        });

        return grades;
    }

    private GradesDetailDTO mapToDto(GradesEntity gradesEntity) {
        GradesDetailDTO dto = new GradesDetailDTO();

        dto.setId(gradesEntity.getId());
        dto.setGrade(gradesEntity.getGrade());
        dto.setTestId(gradesEntity.getTestId());
        dto.setUserId(gradesEntity.getUserId());

        return dto;
    }
}
