package sk.umb.fpv.laflait.section.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;
import sk.umb.fpv.laflait.section.persistance.repository.SectionRepository;
import sk.umb.fpv.laflait.theses.persistance.entity.ThesesEntity;
import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<SectionDetailDTO> getAllSections() {
        return mapToDtoList(sectionRepository.findAll());
    }

    public SectionDetailDTO getSectionByID(Long sectionId) {
        return mapToDto(getSectionEntityByID(sectionId));
    }

    private SectionEntity getSectionEntityByID(Long id) {
        Optional<SectionEntity> entity = sectionRepository.findById(id);

        if(entity.isEmpty()) {
            throw new IllegalArgumentException("Section not found. ID: " + id);
        }

        return entity.get();
    }

    @Transactional
    public void updateSectionByID(Long sectionId, SectionRequestDTO sectionRequestDTO) {
        SectionEntity entity = getSectionEntityByID(sectionId);

        if(!Strings.isEmpty(sectionRequestDTO.getTitle())) {
            entity.setTitle(sectionRequestDTO.getTitle());
        }
        if(!Strings.isEmpty(sectionRequestDTO.getText())) {
            entity.setText(sectionRequestDTO.getText());
        }

        sectionRepository.save(entity);
    }

    private List<SectionDetailDTO> mapToDtoList(Iterable<SectionEntity> sectionEntities) {
        List<SectionDetailDTO> sections = new ArrayList<>();

        sectionEntities.forEach(sectionEntity -> {
            SectionDetailDTO dto = mapToDto(sectionEntity);
            sections.add(dto);
        });

        return sections;
    }

    private SectionDetailDTO mapToDto(SectionEntity sectionEntity) {
        SectionDetailDTO dto = new SectionDetailDTO();

        dto.setId(sectionEntity.getId());
        dto.setTitle(sectionEntity.getTitle());
        dto.setText(sectionEntity.getText());
        dto.setThesesDetailDTO(mapToDto(sectionEntity.getTheses()));

        return dto;
    }

    private ThesesDetailDTO mapToDto(ThesesEntity thesesEntity) {
        ThesesDetailDTO dto = new ThesesDetailDTO();

        dto.setId(thesesEntity.getId());
        dto.setTitle(thesesEntity.getTitle());
        dto.setDescription(thesesEntity.getDescription());

        return dto;
    }


}
