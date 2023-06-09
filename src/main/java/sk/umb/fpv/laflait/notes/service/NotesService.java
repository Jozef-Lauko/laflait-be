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

    private NotesDetailDTO mapToDto(NotesEntity notesEntity) {
        NotesDetailDTO dto = new NotesDetailDTO();

        dto.setId(notesEntity.getId());
        dto.setText(notesEntity.getText());
        dto.setImageData(notesEntity.getImageData());
        dto.setCode(notesEntity.getCode());

        return dto;
    }
}
