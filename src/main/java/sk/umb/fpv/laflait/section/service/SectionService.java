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
import java.util.Optional;

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
        dto.setThesesDetailDTO(mapToDto(thesesRepository.findById(sectionEntity.getId_teza())));

        System.out.println("Nazov Tezy: " + dto.getThesesDetailDTO().getTitle());
        System.out.println("ID_kapitoly: " + dto.getId());
        System.out.println("Nazov_kapitoly: " + dto.getTitle());

        String[] par = dto.getParagraphs();
        for(String p : par) {
            System.out.println(p);
        }

        System.out.println();

        return dto;
    }

    private ThesesDetailDTO mapToDto(Optional<ThesesEntity> thesesEntity) {
        ThesesDetailDTO dto = new ThesesDetailDTO();

        dto.setId(thesesEntity.get().getId());
        dto.setTitle(thesesEntity.get().getTitle());
        dto.setDescription(thesesEntity.get().getDescription());

        return dto;
    }
}
