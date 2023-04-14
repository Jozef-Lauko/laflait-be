package sk.umb.fpv.laflait.subsection.service;

import jakarta.persistence.Column;
import sk.umb.fpv.laflait.notes.service.NotesDetailDTO;
import sk.umb.fpv.laflait.section.service.SectionDetailDTO;

public class SubsectionDetailDTO {

    private Long id;

    private String title;

    private String text;

    private SectionDetailDTO sectionDetailDTO;

    private NotesDetailDTO notesDetailDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SectionDetailDTO getSectionDetailDTO() {
        return sectionDetailDTO;
    }

    public void setSectionDetailDTO(SectionDetailDTO sectionDetailDTO) {
        this.sectionDetailDTO = sectionDetailDTO;
    }

    public NotesDetailDTO getNotesDetailDTO() {
        return notesDetailDTO;
    }

    public void setNotesDetailDTO(NotesDetailDTO notesDetailDTO) {
        this.notesDetailDTO = notesDetailDTO;
    }
}
