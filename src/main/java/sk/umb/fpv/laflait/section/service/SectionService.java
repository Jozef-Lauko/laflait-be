package sk.umb.fpv.laflait.section.service;

import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;
import sk.umb.fpv.laflait.section.persistance.repository.SectionRepository;
import sk.umb.fpv.laflait.theses.persistance.entity.ThesesEntity;
import sk.umb.fpv.laflait.theses.persistance.repository.ThesesRepository;
import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;
import sk.umb.fpv.laflait.theses.service.ThesesService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    private final ThesesRepository thesesRepository;

    public SectionService(SectionRepository sectionRepository, ThesesRepository thesesRepository) {
        this.sectionRepository = sectionRepository;
        this.thesesRepository = thesesRepository;
    }

    public List<SectionDetailDTO> getAllSections() {
        return mapToDtoList(sectionRepository.findAll());
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

        System.out.println("ID: " + dto.getId());
        System.out.println("Nazov: " + dto.getTitle());
        System.out.println("Opis: " + dto.getText());
        System.out.println();

        return dto;
    }

}
