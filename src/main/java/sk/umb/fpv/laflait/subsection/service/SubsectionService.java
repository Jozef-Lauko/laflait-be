package sk.umb.fpv.laflait.subsection.service;

import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;
import sk.umb.fpv.laflait.section.persistance.repository.SectionRepository;
import sk.umb.fpv.laflait.section.service.SectionDetailDTO;
import sk.umb.fpv.laflait.subsection.persistance.entity.SubsectionEntity;
import sk.umb.fpv.laflait.subsection.persistance.repository.SubsectionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubsectionService {
    private final SubsectionRepository subsectionRepository;
    private final SectionRepository sectionRepository;

    public SubsectionService(SubsectionRepository subsectionRepository, SectionRepository sectionRepository) {
        this.subsectionRepository = subsectionRepository;
        this.sectionRepository = sectionRepository;
    }


    public List<SubsectionDetailDTO> getAllSubsections() {
        return mapToDtoList(subsectionRepository.findAll());
    }

    public SubsectionDetailDTO getSubsectionBytID(Long subsectionId) {
        return mapToDto(getSubsectionEntityByID(subsectionId));
    }

    private SubsectionEntity getSubsectionEntityByID(Long Id) {
        Optional<SubsectionEntity> entity = subsectionRepository.findById(Id);

        if(entity.isEmpty()) {
            throw new IllegalArgumentException("Subsection not found. ID: " + Id);
        }

        return entity.get();
    }

    private List<SubsectionDetailDTO> mapToDtoList(Iterable<SubsectionEntity> subsectionEntities) {
        List<SubsectionDetailDTO> subsections = new ArrayList<>();

        subsectionEntities.forEach(subsectionEntity -> {
            SubsectionDetailDTO dto = mapToDto(subsectionEntity);
            subsections.add(dto);
        });

        return subsections;
    }

    private SubsectionDetailDTO mapToDto(SubsectionEntity subsectionEntity) {
        SubsectionDetailDTO dto = new SubsectionDetailDTO();

        dto.setId(subsectionEntity.getId());
        dto.setTitle(subsectionEntity.getTitle());
        dto.setText(subsectionEntity.getText());
        dto.setSectionDetailDTO(mapToDto(sectionRepository.findById(subsectionEntity.getId())));

        return dto;
    }

    private SectionDetailDTO mapToDto(Optional<SectionEntity> sectionEntity) {
        SectionDetailDTO dto = new SectionDetailDTO();

        dto.setId(sectionEntity.get().getId());
        dto.setTitle(sectionEntity.get().getTitle());
        dto.setText(sectionEntity.get().getText());

        return dto;
    }
}

