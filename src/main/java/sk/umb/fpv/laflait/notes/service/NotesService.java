package sk.umb.fpv.laflait.notes.service;

import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.notes.persistance.entity.NotesEntity;
import sk.umb.fpv.laflait.notes.persistance.repository.NotesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotesService {

    private final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<NotesDetailDTO> getAllNotes() {
        return mapToDtoList(notesRepository.findAll());
    }

    private List<NotesDetailDTO> mapToDtoList(Iterable<NotesEntity> entities) {
        List<NotesDetailDTO> notes = new ArrayList<>();

        entities.forEach(notesEntity -> {
            NotesDetailDTO dto = mapToDto(notesEntity);
            notes.add(dto);
        });

        return notes;
    }

    private NotesDetailDTO mapToDto(NotesEntity notesEntity) {
        NotesDetailDTO dto = new NotesDetailDTO();

        dto.setId(notesEntity.getId());
        dto.setText(notesEntity.getText());
        dto.setImages(notesEntity.getImages());
        dto.setLinks(notesEntity.getLinks());
        dto.setCode(notesEntity.getCode());

        return dto;
    }
}
