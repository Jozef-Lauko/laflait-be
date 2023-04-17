package sk.umb.fpv.laflait.subsection.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.exception.LaflaitApplicationException;
import sk.umb.fpv.laflait.notes.persistance.entity.NotesEntity;
import sk.umb.fpv.laflait.notes.persistance.repository.NotesRepository;
import sk.umb.fpv.laflait.notes.service.NotesDetailDTO;
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


    public List<SubsectionDetailDTO> getAllSubsections() {
        return mapToDtoList(subsectionRepository.findAll());
    }

    public SubsectionDetailDTO getSubsectionByID(Long subsectionId) {
        return mapToDto(getSubsectionEntityByID(subsectionId));
    }

    private SubsectionEntity getSubsectionEntityByID(Long Id) {
        Optional<SubsectionEntity> entity = subsectionRepository.findById(Id);

        if(entity.isEmpty()) {
            throw new IllegalArgumentException("Subsection not found. ID: " + Id);
        }

        return entity.get();
    }

    @Transactional
    public void updateSubsectionByID(Long subsectionId, SubsectionRequestDTO subsectionRequestDTO) {
        SubsectionEntity entity = getSubsectionEntityByID(subsectionId);

        if(!Strings.isEmpty(subsectionRequestDTO.getTitle())) {
            entity.setTitle(subsectionRequestDTO.getTitle());
        }

        if(!Strings.isEmpty(subsectionRequestDTO.getText())) {
            entity.setText(subsectionRequestDTO.getText());
        }

        if(subsectionRequestDTO.getSectionID() != null) {
            SectionEntity sectionEntity = mapToEntity(subsectionRequestDTO.getSectionID());
            entity.setSection(sectionEntity);
        }

        if(subsectionRequestDTO.getNotesID() != null) {
            NotesEntity notesEntity = mapToNotesEntity(subsectionRequestDTO.getNotesID());
            entity.setNotes(notesEntity);
        }

        subsectionRepository.save(entity);
    }

    private SectionEntity mapToEntity(Long sectionID) {
        Optional<SectionEntity> entity = sectionRepository.findById(sectionID);

        if(entity.isPresent()) {
            return entity.get();
        }else{
            throw new LaflaitApplicationException("SectionID is invalid.");
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

        if(subsectionEntity.getSection() != null){
            dto.setSectionDetailDTO(mapToDto(subsectionEntity.getSection()));
        }else{
            dto.setSectionDetailDTO(new SectionDetailDTO());
        }

        if(subsectionEntity.getNotes() != null) {
            dto.setNotesDetailDTO(mapToDto(subsectionEntity.getNotes()));
        }else{
            dto.setNotesDetailDTO(new NotesDetailDTO());
        }

        return dto;
    }

    private NotesDetailDTO mapToDto(NotesEntity notesEntity) {
        NotesDetailDTO dto = new NotesDetailDTO();

        dto.setId(notesEntity.getId());
        dto.setText(notesEntity.getText());
        dto.setCode(notesEntity.getCode());
        dto.setImages(notesEntity.getImages());

        return dto;
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

