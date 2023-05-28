package sk.umb.fpv.laflait.section.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.notes.persistance.entity.NotesEntity;
import sk.umb.fpv.laflait.notes.service.NotesDetailDTO;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;
import sk.umb.fpv.laflait.section.persistance.repository.SectionRepository;
import sk.umb.fpv.laflait.subsection.service.SubsectionService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    private final SubsectionService subsectionService;

    public SectionService(SectionRepository sectionRepository, SubsectionService subsectionService) {
        this.sectionRepository = sectionRepository;

        this.subsectionService = subsectionService;
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
        entity.setNotesEntity(mapToNotesEntity(sectionRequestDTO.getNotesid(), sectionRequestDTO.getNotestext(), sectionRequestDTO.getNotescode(), sectionRequestDTO.getNotesimageData()));

        sectionRepository.save(entity);
    }

    private NotesEntity mapToNotesEntity(Long notesid, String notestext, String notescode, byte[] notesimageData) {
        NotesEntity entity = new NotesEntity();

        if(notesid == null){
            entity.setId((long) -1);
            notesimageData = new byte[0];
            entity.setImageData(notesimageData);
        }else{
            entity.setId(notesid);
            entity.setImageData(notesimageData);
        }

        entity.setCode(notescode);
        entity.setText(notestext);

        return entity;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<SectionDetailDTO> getSectionsByThesisID(Long thesisID) {
        return mapToDtoList(sectionRepository.findAllByTeza(thesisID));
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

        NotesEntity notesEntity = sectionEntity.getNotesEntity();
        if (notesEntity != null) {
            dto.setNotesid(notesEntity.getId());
            dto.setNotestext(notesEntity.getText());
            dto.setNotescode(notesEntity.getCode());
            dto.setNotesimageData(notesEntity.getImageData());
        }

        if(!subsectionService.getAllSubsectionsBySectionID(sectionEntity.getId()).isEmpty()) {
            dto.setHaveSubsection(true);
        }else{
            dto.setHaveSubsection(false);
        }

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
