package sk.umb.fpv.laflait.subsection.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.exception.LaflaitApplicationException;
import sk.umb.fpv.laflait.notes.persistance.entity.NotesEntity;
import sk.umb.fpv.laflait.notes.persistance.repository.NotesRepository;
import sk.umb.fpv.laflait.notes.service.NotesDetailDTO;
import sk.umb.fpv.laflait.notes.service.NotesRequestDTO;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;
import sk.umb.fpv.laflait.section.persistance.repository.SectionRepository;
import sk.umb.fpv.laflait.section.service.SectionDetailDTO;
import sk.umb.fpv.laflait.subsection.persistance.entity.SubsectionEntity;
import sk.umb.fpv.laflait.subsection.persistance.repository.SubsectionRepository;
import sk.umb.fpv.laflait.theses.persistance.entity.ThesesEntity;
import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubsectionService {
    private final SubsectionRepository subsectionRepository;
    private final SectionRepository sectionRepository;
    private final NotesRepository notesRepository;

    public SubsectionService(SubsectionRepository subsectionRepository, SectionRepository sectionRepository, NotesRepository notesRepository) {
        this.subsectionRepository = subsectionRepository;
        this.sectionRepository = sectionRepository;
        this.notesRepository = notesRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<SubsectionDetailDTO> getAllSubsectionsBySectionID(Long sectionID) {
        return mapToDtoList(subsectionRepository.findAllByKapitola(sectionID));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public SubsectionDetailDTO getSubsectionByID(Long subsectionId) {
        return mapToDto(getSubsectionEntityByID(subsectionId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    private SubsectionEntity getSubsectionEntityByID(Long Id) {
        Optional<SubsectionEntity> entity = subsectionRepository.findById(Id);

        if(entity.isEmpty()) {
            throw new IllegalArgumentException("Subsection not found. ID: " + Id);
        }

        return entity.get();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void updateSubsectionByID(Long subsectionId, SubsectionRequestDTO subsectionRequestDTO) {
        SubsectionEntity entity = getSubsectionEntityByID(subsectionId);

        if(!Strings.isEmpty(subsectionRequestDTO.getTitle())) {
            entity.setTitle(subsectionRequestDTO.getTitle());
        }

        if(!Strings.isEmpty(subsectionRequestDTO.getText())) {
            entity.setText(subsectionRequestDTO.getText());
        }
        entity.setNotesEntity(mapToNotesEntity(subsectionRequestDTO.getNotesid(), subsectionRequestDTO.getNotestext(), subsectionRequestDTO.getNotescode(), subsectionRequestDTO.getNotesimageData()));


        subsectionRepository.save(entity);
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

        NotesEntity notesEntity = subsectionEntity.getNotesEntity();
        if (notesEntity != null) {
            dto.setNotesid(notesEntity.getId());
            dto.setNotestext(notesEntity.getText());
            dto.setNotescode(notesEntity.getCode());
            dto.setNotesimageData(notesEntity.getImageData());
        }

        return dto;
    }

    private NotesDetailDTO mapToDto(Optional<NotesEntity> notesEntity) {
        NotesDetailDTO dto = new NotesDetailDTO();

        dto.setId(notesEntity.get().getId());
        dto.setText(notesEntity.get().getText());
        dto.setCode(notesEntity.get().getCode());
        dto.setImageData(notesEntity.get().getImageData());

        return dto;
    }

}

