package sk.umb.fpv.laflait.section.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.exception.LaflaitApplicationException;
import sk.umb.fpv.laflait.notes.persistance.entity.NotesEntity;
import sk.umb.fpv.laflait.notes.persistance.repository.NotesRepository;
import sk.umb.fpv.laflait.notes.service.NotesDetailDTO;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;
import sk.umb.fpv.laflait.section.persistance.repository.SectionRepository;
import sk.umb.fpv.laflait.theses.persistance.entity.ThesesEntity;
import sk.umb.fpv.laflait.theses.persistance.repository.ThesesRepository;
import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    private final ThesesRepository thesesRepository;
    private final NotesRepository notesRepository;

    public SectionService(SectionRepository sectionRepository, ThesesRepository thesesRepository, NotesRepository notesRepository) {
        this.sectionRepository = sectionRepository;
        this.thesesRepository = thesesRepository;
        this.notesRepository = notesRepository;
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
        //TODO

        sectionRepository.save(entity);
    }

    public List<SectionDetailDTO> getSectionsByThesisID(Long thesisID) {
        return mapToDtoList(sectionRepository.findAllByTeza(thesisID));
    }

    private ThesesEntity mapToThesisEntity(Long thesisID) {
        Optional<ThesesEntity> entity = thesesRepository.findById(thesisID);

        if(entity.isPresent()) {
            return entity.get();
        }else{
            throw new LaflaitApplicationException("ThesisID is invalid.");
        }
    }

    private NotesEntity mapToNotesEntity(Long notesID) {
        Optional<NotesEntity> entity = notesRepository.findById(notesID);

        if(entity.isPresent()) {
            return entity.get();
        }else{
            throw new LaflaitApplicationException("NotesID is invalid.");
        }
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
        dto.setThesesID(sectionEntity.getThesesID());
        dto.setNotesID(sectionEntity.getNotesID());

        return dto;
    }

}
