package sk.umb.fpv.laflait.section.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.notes.persistance.entity.NotesEntity;
import sk.umb.fpv.laflait.notes.persistance.repository.NotesRepository;
import sk.umb.fpv.laflait.notes.service.NotesDetailDTO;
import sk.umb.fpv.laflait.notes.service.NotesRequestDTO;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;
import sk.umb.fpv.laflait.section.persistance.repository.SectionRepository;
import sk.umb.fpv.laflait.theses.persistance.repository.ThesesRepository;


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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public SectionDetailDTO getSectionByID(Long sectionId) {
        return mapToDto(getSectionEntityByID(sectionId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    private SectionEntity getSectionEntityByID(Long id) {
        Optional<SectionEntity> entity = sectionRepository.findById(id);

        if(entity.isEmpty()) {
            throw new IllegalArgumentException("Section not found. ID: " + id);
        }

        return entity.get();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void updateSectionByID(Long sectionId, SectionRequestDTO sectionRequestDTO) {
        SectionEntity entity = getSectionEntityByID(sectionId);

        if(!Strings.isEmpty(sectionRequestDTO.getTitle())) {
            entity.setTitle(sectionRequestDTO.getTitle());
        }
        if(!Strings.isEmpty(sectionRequestDTO.getText())) {
            entity.setText(sectionRequestDTO.getText());
        }
        entity.setNotesEntity(mapToNotesEntity(sectionRequestDTO.getNotes()));

        sectionRepository.save(entity);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<SectionDetailDTO> getSectionsByThesisID(Long thesisID) {
        return mapToDtoList(sectionRepository.findAllByTeza(thesisID));
    }

    private NotesEntity mapToNotesEntity(NotesRequestDTO notes) {
        NotesEntity entity = new NotesEntity();

        entity.setId(notes.getId());
        entity.setText(notes.getText());
        entity.setCode(notes.getCode());
        entity.setImageData(notes.getImageData());

        return entity;
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
        dto.setNotesDTO(mapToDto(Optional.ofNullable(sectionEntity.getNotesEntity())));

        return dto;
    }

    private NotesDetailDTO mapToDto(Optional<NotesEntity> notesEntity){
        NotesDetailDTO dto = new NotesDetailDTO();

        if(notesEntity.isPresent()){
            dto.setId(notesEntity.get().getId());
            dto.setText(notesEntity.get().getText());
            dto.setCode(notesEntity.get().getCode());
            dto.setImageData(notesEntity.get().getImageData());
        }

        return dto;
    }

}
